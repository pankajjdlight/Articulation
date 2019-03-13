#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include "mfcc_defines.h"
#include "mfcc_globals.h"


// Dependency Files :::   STARTING_POINT, END_POINT -->  is the file where frame Starting point and End point ...... now merged to one file and taken from 
//                        command prompt .... 

/**
           Function Name: vad_enrthr
           Function Syntax: short * vad_enrthr(  char fullpath_input[], int *total_no_of_frames, int *no_of_speech_frames)
         
           Inputs:
                    @param fullpath_input          - consists fullpath of input file name.
           Outputs:
                    @param total_no_of_frames      - returns the total no of frames with overlapping.
                    @param no_of_speech_frames     - returns the no of speech frames.
                    @param nof_fsize               - no. of frames of frame size without overlapping.
                    @return speech_nonspeech_frames - returns the boolean array of size equal to total no. of frames, for a speeched frame value is 1 and for nonspeeched frame value is 0.        

           Description:
                    Voice Activity Detection(vad) is to calculate the number of Speeched frames, total number of frames and speeched_nonspeeched Array.
                    waveStruct and globDataSpeechRecgStruct have been defined in mfcc_globals.h.
                     
*/
FILE *ENRGY;
short *vad_enrthr(char fullpath_input[], int *total_no_of_frames, int *no_of_speech_frames, int *nof_fsize, char strt_frame_fileName[], char end_frame_fileName[], 
                  char voiced_unvoiced_fileName[], char speech_frameNo_fileName[], char avrEgy_FileName[], float *Energy_frames){
    
    ENRGY = fopen("enrgy.txt" , "w");
	short *ptr;
        
	int i, j, k = 0, m, start_point, end_point ,c=0 ,p=0; 
	int shift=0, incr = 0;
        int nof_fshift;                           // Actual no of frames of FRAMESIZE obtained after FRAMESHIFT
        int sum_energy=0;
        short *speech_nonspeech_frames;
	float energy, Avg_Energy = 0;
	//float *Energy_frames;
	short temp_buff[FRAMESIZE];	          // for temporay storage
        long eoinput;
        int temp_no_speech_frames;
        FILE *fp1_input, *fp2_input, *fp_speech_nonspeech, *fp_starting_end_point, *fp_endpoint,  *fp_no_speech_frames, *fp_avr_enr, *fp_total_no_frames, *fp_nof_fsize;
        
        *no_of_speech_frames = 0;                // used to calculate the no. of speech frames


	//printf(" I/P File is \t %s \n",fullpath_input);
	if((fp1_input = fopen(fullpath_input,"rb")) == NULL)                               // Opens the input wav file to count the no of frames without FRAMESHIFT
		{
			//printf("Unable to open the file %s \n",fullpath_input);
			exit(0);
		}
	else
	{
		fread(&globDataSpeechRecgStruct.header,sizeof(waveStruct),1,fp1_input);	              // Reads input file header information
               
	}
	
 
	if((fp2_input = fopen(fullpath_input,"rb")) == NULL)                               // Opens the input wav file
		{
			//printf("Unable to open the %s file\n",fullpath_input); 
			exit(0);
		}
	else
	{
		fseek(fp2_input, sizeof(waveStruct), SEEK_SET);                           // Moving the pointer next to header data
	}   
       
	     

	
	while(1==1)                                                                         // Calculating total no. of frames without overlapping.
		{
			fread(temp_buff, sizeof(short), FRAMESIZE, fp1_input);              // Using temp_buff for just a temporary fread()

                        if(feof(fp1_input)) break;

			(*nof_fsize)++;
                        //printf("\n\n\n\n\n\nCCCHHHEEECCCCCKKKKKKK %d\n\n\n\n\n\n\n\n\n",*nof_fsize);
		}


	fclose(fp1_input);

        //printf("no of frame_size\t%d \n",*nof_fsize);
        //nof_fshift=((FRAMESIZE/FRAMESHIFT)*(*nof_fsize))-((FRAMESIZE/FRAMESHIFT)-1);   // Calculating the no of frames of FRAMESHIFT
        nof_fshift = floor((FRAMESIZE * (*nof_fsize))/FRAMESHIFT) + 1;
        //printf("no of frame shift\t%d\n", nof_fshift);
	ptr = (short*)malloc((FRAMESIZE * (*nof_fsize)) * sizeof(short));              // Allocating contiguous memory to store the complete data of Input wav file

	Energy_frames = (float *)malloc( nof_fshift * sizeof(float) );                // Allocating contiguous memory to store the energies of every frame
        //printf("ENERGY_FRAMES IS \t%f\n", *Energy_frames);
        
        speech_nonspeech_frames = (short *)malloc( nof_fshift * sizeof(short) );       // Allocating contiguous memory to store the boolean value whether a frame is speech or non-speech 


        fread(ptr, sizeof(short), (*nof_fsize)*FRAMESIZE, fp2_input);  
     //   printf("pointer value%d",*ptr);// Reading complete data as single chunk
			
	fclose(fp2_input);

	shift = 0;

       eoinput= ( (*nof_fsize) * FRAMESIZE ) - FRAMESHIFT;
      // printf("endofinput....(vad)\t %ld  \n", eoinput);
 /**
     The below while loop is used for calculating the energy of each frame and storing it in the Energy_frames array

*/       
       int L2= (eoinput/FRAMESHIFT)-2;
       //printf("L2 is %d\n",L2);
      
	while(shift != eoinput)
		{
			        for(i=0; i<FRAMESIZE; i++)                    // Calculating channel data for one frame of 20 milli seconds
				  {
					
                                                //printf("Ptr is %hd\t ", *ptr);
						globDataSpeechRecgStruct.sample_value[i] = (float)(*ptr)/(float)pow(2,(globDataSpeechRecgStruct.header.Bits_Per_Sample-1));   
                                                ptr++;
                                                //printf(" %f\t ", globDataSpeechRecgStruct.sample_value[i]);
								
				   }
                        

					energy = 0;
					for(i=0; i<FRAMESIZE; i++)           // Calculating energy of the frame
						{
							energy = (float)energy + (float)pow(globDataSpeechRecgStruct.sample_value[i], 2);
                                                        //printf(" %f\t ", energy);
						}
                                        
					Energy_frames[incr] = energy;                                    
					incr++;
                                        

                        
                        ptr = ptr- (FRAMESIZE- FRAMESHIFT);
			shift = shift + FRAMESHIFT;		
                                     								

		}                                     // While Loop ends


       //printf("%d", FRAMESIZE);
      for(i=0; i<L2; i++)                    // Calculating average energy
      {
	Avg_Energy = Avg_Energy + Energy_frames[i]; 
        //printf("energy is : %f\n",Energy_frames[i]);
        fprintf(ENRGY,"%f \t ",Energy_frames[i]);  
        //printf("Average energy is : %f\n",Avg_Energy);
      }
       Avg_Energy = Avg_Energy/L2;
       //printf("Average energy is : %f\n",Avg_Energy);
       
       
       int b1[L2], b2[L2];
       int first[p],second[p];
       for(int i=0; i<=L2; i++)
       {
        if((Energy_frames[i])>(0.1*Avg_Energy))
            c=c+1;
        //printf("C is %d\t", c);
           {
            if(c>5)
               {
                //printf("Result is %d\n", i);
                b1[p]=((i-5)*FRAMESHIFT);
               
                //printf("Result is %d\n", b1[p]);
                
                 p++;
               }
            first[p]=b1[1];
            first[p]=first[p]/FRAMESHIFT;
           }
            
       }   
     //  printf("1st is (b1) %d\n",first[p]);
       
       
       Avg_Energy = Avg_Energy/L2;
       
            
       c=0; p=0;
       for(int i=0; i<=L2+1; i++)
       {
        if((Energy_frames[i])<(0.2*Avg_Energy))
            c=c+1;
        //printf("C is %d\t", c);
           {
            if(c>2)
               {
               // printf("Result is %d\n", i);
                b2[p]=((i-5)*FRAMESHIFT);
                
                //printf("Result in 2nd is %d\n", b2[p]);
                p++;
                second[p]=b2[53];
                second[p]=second[p]/FRAMESHIFT;
               }
            }
       }
       
       
     //  printf("2nd is (b2) %d\n",second[p]);
       
       
       
       //printf("%d", FRAMESIZE);
fclose(ENRGY); 
/*for(i=0;i<nof_fshift;i++)
{
printf("energy_frames : %f\n",Energy_frames[i]);
}*/


/*************** Comparing with threshold energy and passing 1 if the frame is speech frame else 0 ***********************/
          while( k<=nof_fshift )
          {
            for( m=0;m<4;m++)
           {

            if((Energy_frames[k+m] > (0*Avg_Energy))) 
	     {
					
                speech_nonspeech_frames[k+m]=1;                   
                //(*no_of_speech_frames)++;
			
            }

            else
            {
               speech_nonspeech_frames[k+m]=0;
            } 

           }
        for( m=0;m<4;m++)
        {
          sum_energy=sum_energy + speech_nonspeech_frames[k+m];
        }
       
        if(sum_energy==4)
         break;
         
        else
         { 
            sum_energy=0;
            k++;
         }
 				
         }

   //printf( " starting point is : %d\n", k);
   start_point=k;
  
  
   
        if( (fp_starting_end_point = fopen(strt_frame_fileName,"w") ) == NULL)
	  {
            //printf("......EXIT.....");
		//printf("Unable to open the file %d \n",*no_of_speech_frames);
		exit(0);
	  }
	else
	{
                fprintf(fp_starting_end_point,"%d\n",k);
	}

       k= nof_fshift;   
       sum_energy=0;
        while( k>=0 )
          {
            for( m=0;m<4;m++)
           {

            if((Energy_frames[k-m] > (0*Avg_Energy))) 
	     {
					
                speech_nonspeech_frames[k-m]=1;                   
                
			
            }

            else
            {
               speech_nonspeech_frames[k-m]=0;
            } 

           }
        for( m=0;m<4;m++)
        {
          sum_energy=sum_energy + speech_nonspeech_frames[k-m];
        }
       
        if(sum_energy==4)
         break;
         
        else
         { 
            sum_energy=0;
            k--;
         }
 				
         }

   //printf( " end point is : %d\n", k);
   end_point=k;
   
   /*if(k>=300)
   {
    k= nof_fshift;   
       sum_energy=0;
        while( k>=0 )
          {
            for( m=0;m<4;m++)
           {

            if((Energy_frames[k-m] > (0.25*Avg_Energy))) 
	     {
					
                speech_nonspeech_frames[k-m]=1;                   
                
			
            }

            else
            {
               speech_nonspeech_frames[k-m]=0;
            } 

           }
        for( m=0;m<4;m++)
        {
          sum_energy=sum_energy + speech_nonspeech_frames[k-m];
        }
       
        if(sum_energy==4)
         break;
         
        else
         { 
            sum_energy=0;
            k--;
         }
 				
         }
   }*/

    if( (fp_endpoint = fopen(end_frame_fileName,"w") ) == NULL)
	  {
         //printf("......EXIT.....");
		//printf("Unable to open the file %d \n",*no_of_speech_frames);
		exit(0);
	  }
	else
	{
                fprintf(fp_endpoint,"%d\n",k);
	}


k=0;

/*************** Comparing with threshold energy and passing 1 if the frame is speech frame else 0 ***********************/
          while( k<=nof_fshift )
          {
            if((Energy_frames[k] > (0*Avg_Energy))) 
	     {
					
                speech_nonspeech_frames[k]=1;                   
                (*no_of_speech_frames)++;
			
            }

            else
            {
               speech_nonspeech_frames[k]=0;
            } 

            k++;
 				
         }

        temp_no_speech_frames = *no_of_speech_frames;
   
        if( (fp_no_speech_frames = fopen(speech_frameNo_fileName,"w") ) == NULL)
	  {
          
                //printf("Unable to open the file %d \n",*no_of_speech_frames);
		exit(0);
	  }
	else
	{
                fprintf(fp_no_speech_frames,"%d\n",temp_no_speech_frames);
	}


        if( (fp_avr_enr = fopen(avrEgy_FileName,"w") ) == NULL)
	  {
           
                //printf("Unable to open the file %f \n",Avg_Energy);
		exit(0);
	  }
        else
          {
            if( Avg_Energy < 0.0054 )
            {
             fprintf( fp_avr_enr, "F");
            // printf("Result is : %c\n", 'F');
            }
           else
            {
             fprintf( fp_avr_enr, "P");
            // printf("Result is : %c\n", 'P');
            }
  
          }

		
	if((fp_speech_nonspeech = fopen(voiced_unvoiced_fileName,"w")) == NULL)
	  {
                //printf("Unable to open the file %s \n",SPEECH_NONSPEECH);
		exit(0);
	  }
	else
	{
		for(i=0;i<nof_fshift;i++)                       //WRITING SPEECH_NONSPEECH FRAMES TO A FILE
                fprintf(fp_speech_nonspeech,"%hd\n",speech_nonspeech_frames[i]);
	}

       //printf("SPEECH NON SPEECH (print in vad) \t%d\n", temp_no_speech_frames);
	      
                /*ptr = ptr - eoinput;
                printf("no of frame shift 1\t%d\n", nof_fshift);  
                 printf("ENERGY_FRAMES IS \t%f\n", *Energy_frames);
		free(ptr);
                printf("no of frame shift 2\t%d\n", nof_fshift);  
		//free(Energy_frames);
                printf("no of frame shift 3\t%d\n", nof_fshift);  
              
                fclose(fp_starting_end_point);
                fclose(fp_endpoint);*/
                 
                *total_no_of_frames = nof_fshift;
                /*printf("Total no of frames \t %d \n", *total_no_of_frames );
               int count =0; 
                for (int i=0;i<nof_fshift;i++){
                    if(speech_nonspeech_frames[i]==1)
                        count++;
                
                }*/
               //printf("\n\n\nthe shift is %d\n\n",nof_fshift);
               //printf("\n\n\nthe count is %d\n\n",count);
             //  *total_no_of_frames = count;
		return(speech_nonspeech_frames);		
}	// vad_enrthr() ENDS HERE


/**

            JOB:  Implementation of Voice Activity Detection(vad)

                   (a) This module is calculating the total no. of frames, number of speech frames and speech_nonspeech_frames array.
                   (b) Through the fullpath of the input file name, the file(.wav) is read to calculate the total number of frames.
                   (c) Once the total number of frames are calculated, the sample values are calculated from the file for each frame of size FRAMESIZE with a shift of FRAMESHIFT.
                   (d) From the channeldata the energy is calculated by finding the absolute square of channeldata. 
                   (e) Once the energy is calculated for all the frames and stored in Energy_Frames[], average energy is calculated from the Energy_Frames[] by summing up all
                       the elements of Energy_Frames[] and dividing by total number of frames.
                   (f) Energy of each frame is then compared to a threshold of (0.006 * average energy), if the energy of a particular frame is greater than the threshold, the value of speech_nonspeech_frames 
                       array for that frame is set to '1'  and if energy of a particular frame is lesser than the threshold, the value is set to '0'.
                   (g) Once the loop is over, no_of_speech_frames contains the number of speech frames and the speech_nonspeech_frames array is written to the file SPEECH_NONSPEECH.
                       Hence the module returns two outputs, number of speech frames and speech_nonspeech_frames array.
       
            Inputs  - fullpath of the input file name i.e. the file name of the current speaker undergoing training.
                         

            Outputs - total no of frames of the input file with overlapping.
                    - number of speech frames i.e frames whose energy is greater than threshold energy.
                    - speech_nonspeech_frames which is the array of total number of frames having value '1' or '0'. The value of speech_frame is '1' if the energy of the frame is greater than threshold and '0' if lesser than        
                           threshold.
                    - no. of frames of frame size without overlapping.
           
 Author: 
 Creation Date : 
 Copyright:       
            
***************************************************************************
 Log of Changes
   
            Last Updated: 
            Update Notes: 

********************************************************************************/









