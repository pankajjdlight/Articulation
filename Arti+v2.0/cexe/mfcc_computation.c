#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<math.h> 
#include "mfcc_defines.h"
#include "mfcc_globals.h"

//float   **memory_alloc_2D(int, int);
void     hamming(float input[], float output[]);
void     dft(float real_input[], float final_arg[]);
void     frequency_warping_spectrum(float Filter_resp[][DFT_POINT], float dft_value[], float spectrum_out[]);
void     Log_spectrum(float energy_output[], float log_out[]);
void     twoDimensional_DCT(float log_out[], float A_out[],int totalFrames);
void     intelligibility( float log_out[], int totalFrames);
void     automatic_selection( float mahal_out[],char *x, int totalFrames);
void     IDCT(float log_values[], float IDCT_out[]);
void     cms(float **mfccCoeffs, int totalFrames,int numCoeffs);
void     ZeroMeanUnitVariance(float **features, int totalFrames,int numCoeffs);
void     filter_bank(float sample_rate, float Filter_resp[][DFT_POINT]);
float    **ComputeDeltaCoefficients(float **inpFeatures, int numFeatures, int dimFeats, int K);
char     *mfcc_computation(char id[], int total_no_of_frames, char fullpath_input[], int nof_fsize, char DirNameforMfccFeatures[]);
float    **ComputeMFCC(char waveName[], int numSpeechFrames, short *speechNonSpeech, int nof_fsize, int totalFrames,char *x);
float    **ConcatenateFeatures(float **feature1, int rows1, int cols1, float **feature2, int rows2, int cols2);
float    **ConcatenateFeaturesWithOffset(float **feature1, int rows1, int cols1, float **feature2, int rows2, int cols2, int offset);

/*****    FUNCTION_DECLARATION_ENDS ***************************************************************/


    
   
// Assumed  dim(feature1) = dim(feature2) + 2 * K with K offsets from the beginning and End .... 
float **ConcatenateFeaturesWithOffset(float **feature1, int rows1, int cols1, float **feature2, int rows2, int cols2, int offset){
    float **concatfeat1feat2 = (float **) calloc(rows2, sizeof(float *));
    int iter, dimIter;
    for(iter = offset; iter <  rows1 - offset; iter++){
              concatfeat1feat2[iter - offset] = (float *) calloc(cols1 + cols2, sizeof(float));
              
             // copy the feature1 values for each of the dimension to the concatenated array  .... 
 
              for(dimIter = 0; dimIter < cols1; dimIter++)
                          concatfeat1feat2[iter - offset][dimIter] = feature1[iter][dimIter];

               
             // copy the feature2 values for each of the dimension to the concatenated array  .... 
 
              for(dimIter = 0; dimIter < cols2; dimIter++)
                          concatfeat1feat2[iter - offset][dimIter + cols1] = feature2[iter - offset][dimIter];
             

                                                   } // END_OF_FOR_LOOP_VARiable <<< iter >>>

     return concatfeat1feat2;
                                                                                                          
                                                                                                                        } // END_OF_FUNCTION_Concatenate_Features ....

// It is deemed necessary to have rows1 == rows2  ....
float **ConcatenateFeatures(float **feature1, int rows1, int cols1, float **feature2, int rows2, int cols2){
    // rows1 == rows2  .... is assumed ....   and in general cols1 != cols2 ..... 
    
  
    float **concatfeat1feat2 = (float **) calloc(rows2, sizeof(float *));
    int iter, dimIter;
    if (rows1 != rows2){
         //printf("Error in Concatenating ");
         return concatfeat1feat2;
                       }
                  
    for(iter = 0; iter <  rows1 ; iter++){
              concatfeat1feat2[iter] = (float *) calloc(cols1 + cols2, sizeof(float));
              
             // copy the feature1 values for each of the dimension to the concatenated array  .... 
 
              for(dimIter = 0; dimIter < cols1; dimIter++)
                           concatfeat1feat2[iter][dimIter] = feature1[iter][dimIter];

               
             // copy the feature2 values for each of the dimension to the concatenated array  .... 
 
              for(dimIter = 0; dimIter < cols2; dimIter++)
                          concatfeat1feat2[iter][dimIter + cols1] = feature2[iter][dimIter];
             

                                                   } // END_OF_FOR_LOOP_VARiable <<< iter >>>

     return concatfeat1feat2;
                                                                                                          
                                                                                                                        } // END_OF_FUNCTION_Concatenate_Features ....


FILE *SAMPLES_1, *SAMPLES_2, *SAMPLES_OUT;
float **ComputeMFCC(char waveName[], int numSpeechFrames, short *speechNonSpeech, int nof_fsize, int totalFrames,char *x){
short     *ptr;
int        i,j,k=0,m=0,shift=0, w,count=0; 
int static count1;
int        frameNo = 0, speechFrame = 0;
FILE      *fp_input;
long       eoinput;
float      **mfccCoeffs;


//double     value;
//float      array[eoinput];

SAMPLES_1 = fopen("Samples_1.txt", "a");
SAMPLES_2 = fopen("Samples_2.txt", "a");
SAMPLES_OUT = fopen("Samples_out.txt", "a");
//printf("parameter recived is-->%c",*x);
//printf("TOTAL NO OF FRAMES \t %d\n",totalFrames);

  


///printf("\n\n\n\n\n\nCCCHHHEEECCCCCKKKKKKK %d\n\n\n\n\n\n\n\n\n",totalFrames);

mfccCoeffs = (float **) calloc(totalFrames, sizeof(float *));  // Define the Mfcc Coefficients to have <<< NUMSPEECHFRAMES >>>  
if( ( fp_input=fopen(waveName,"rb") ) == NULL){
   //printf("Cannot open the file");
   exit(0);
                                               }
else{    
     fread(&globDataSpeechRecgStruct.header,sizeof(waveStruct),1,fp_input);  //reading the header data of input(.wav) file
     }

       

filter_bank(globDataSpeechRecgStruct.header.Sample_Rate, globDataSpeechRecgStruct.Filter_resp); // Returns the Filter response which is a 2d matrix of 22*256 elements
ptr = (short*)malloc((FRAMESIZE * nof_fsize) * sizeof(short));    // For Storing the entire Data .... 

fread(ptr, sizeof(short), nof_fsize*FRAMESIZE, fp_input);          //reading complete data as single chunk
fclose(fp_input);
//printf("ptr is %hd \n",*ptr);



eoinput = (nof_fsize * FRAMESIZE) - FRAMESHIFT;  
//printf("no F size is %d \n",nof_fsize);
//printf("end of input is (mfcc_comp) %ld \n",eoinput);



///printf("\n\n\n\n\n\nCCCHHHEEECCCCCKKKKKKK %d\n\n\n",shift);

          while((shift != eoinput)){
              count++;
              ////printf("*PTR 0 IS  \t%hd\n",*ptr);
             
           //   if (speechNonSpeech[frameNo] == 1){ // Check Whether the frame is Speech or not .... 
                          // Declare the array to contain sufficient number of MFCC Coefficients ..... 
                            
                           mfccCoeffs[speechFrame] = (float *) calloc(NUM_OF_COEFFICIENTS, sizeof(float )); 
                            
                           for(i=0; i<DFT_POINT; i++) {
                              ///printf("Count1 is \t\t%d\n",count1);
					      if(i<FRAMESIZE){
                                                  ///printf("BITS_PER_SAMPL: \t %hd\n", globDataSpeechRecgStruct.header.Bits_Per_Sample);
                                                   // /printf("%hd\t",*ptr);
                                                  
                                                  	globDataSpeechRecgStruct.sample_value[i] = (float)(*ptr)/(float)pow(2,(globDataSpeechRecgStruct.header.Bits_Per_Sample)-1);  
                                                        ptr++;
                                                        count1++;
                                                        fprintf(SAMPLES_1," \t%f\t", globDataSpeechRecgStruct.sample_value[i] );
                                                        
                                                        
                                                        
                                                        ///printf(" %f\t ", globDataSpeechRecgStruct.sample_value[i]);
                                                        count1++;
                                                        ///printf("Count1 is \t%d\t",count1);
                                                        ///printf("*PTR %d IS  \t%hd\t ",i,*ptr);
                                                        
                                                        if(shift>=5500)
                                                        {
                                                           ///printf(" the req val is \t%f\t ", globDataSpeechRecgStruct.sample_value[i]); 
                                                        }
                                                    
                                                  //ptr++;
						  }
                                              
				              else{
							globDataSpeechRecgStruct.sample_value[i] = 0;     //assigning zero from 161 to 512 samples
                                                         ///printf("sample_values 1 \t %f\n", globDataSpeechRecgStruct.sample_value[i]);
                                                        fprintf(SAMPLES_2," %f \t", globDataSpeechRecgStruct.sample_value[i] );
						  }
	
				         }
                           //fprintf(SAMPLES_1," \n");
                           fprintf(SAMPLES_OUT," %f \t", globDataSpeechRecgStruct.sample_value[i] );
                         
                         

					hamming(globDataSpeechRecgStruct.sample_value, globDataSpeechRecgStruct.hamming_output1);  //calculates the hamming output
                                        
                                       
                                        
                                        dft(globDataSpeechRecgStruct.hamming_output1, globDataSpeechRecgStruct.final_arg1);        //computes the 512 point DFT
                                       
					frequency_warping_spectrum(globDataSpeechRecgStruct.Filter_resp, globDataSpeechRecgStruct.final_arg1, globDataSpeechRecgStruct.warping_spectrum1); //calculates the warping spectrum
                                        
					Log_spectrum(globDataSpeechRecgStruct.warping_spectrum1, globDataSpeechRecgStruct.log_magnitude1);    //calculates the log magnitude
                                       
                                        
                                           
                                        
					
                                        IDCT(globDataSpeechRecgStruct.log_magnitude1, globDataSpeechRecgStruct.IDCT_Output1);                 //computes the Inverse Discrete cosine transform                      


                                       // /printf(" Printing the MFCC COeffs \n");
                                        for(i=1; i<=NUM_OF_COEFFICIENTS; i++){
                                               mfccCoeffs[speechFrame][i-1] =   globDataSpeechRecgStruct.IDCT_Output1[i]; // Finally Storing the MFCC_COEFS in
                                              // /printf("  %f ",     mfccCoeffs[speechFrame][i-1]);                                                                     
						//	fprintf(fp_coeff, "%1.10f\n", globDataSpeechRecgStruct.IDCT_Output1[i]);             //printing the mfcc coefficients to coefficients.txt file 
						                              } 
                                        speechFrame++; // Increment the Counter of number Of Speech Frames ....  
                                         				 
                                                                  //    } // END_OF_IF_LOOP <<< if (speechNonSpeech[frameNo] == 1)>> I.E. SPEECH_FRAME 
                       frameNo++;
                      // //printf("FRAME NO IS\t%d\n",frameNo);
                      // //printf("*PTR 2 IS\t%hd\n\n",*ptr);
                       shift = shift + (FRAMESHIFT);                                // shift incremented by FRAMESHIFT
                       ptr = ptr - (FRAMESIZE - FRAMESHIFT);                                      // ptr decremented by frameshift
                       k=k+1;
                       ////printf("*PTR o/p IS\t%hd\n",*ptr);
                       ////printf("\t\tCOUNT IS %d\n\n", count);
        
////printf("\n\n\n\n\n\nCCCHHHEEECCCCCKKKKKKK %d\n\n\n",shift);  
////printf("%d", k);
          }     // while LOOP ends
////printf("\n\n\n\n\n\nCCCHHHEEECCCCCKKKKKKK %d\n\n\n",shift);  


  
ptr = ptr - eoinput;
return   mfccCoeffs;
automatic_selection(globDataSpeechRecgStruct.mahal_out,x, totalFrames);
intelligibility(globDataSpeechRecgStruct.log_magnitude1, totalFrames);
 
//printf("\n Checkline %s",ptr);
printf("\n Checkline");

 //free(ptr);	// Free the allocated Pointer ....... 

//  Cepstral Mean Subtraction Logic is implemented only if CHANNELFLAG is 1. 

          // if(CHANNELFLAG == 1){
            //       cms(mfccCoeffs, totalFrames,NUM_OF_COEFFICIENTS);  // calling the cepstral mean subtraction function to subtract the mean from the coefficients and save the mean subtracted coefficients in file
              //                   }
 
 
 
 
 
  
 // automatic_selection(globDataSpeechRecgStruct.mahal_out,x, totalFrames);
  //intelligibility(globDataSpeechRecgStruct.log_magnitude1, totalFrames);
  
                // patching and 2DDCT
 //printf("%c x before use\t",*x);
 
 
 
 fclose(SAMPLES_1);
 fclose(SAMPLES_2);
 fclose(SAMPLES_OUT);
  
                                           }   //END_OF_FUNCTION_ComputeMFCC() 











float **ComputeDeltaCoefficients(float **inpFeatures, int numFeatures, int dimFeats, int K){
    //  The inpFeatures is of dimension (numFeatures * dimFeats) and can be referred as inpFeatures[2][0] etc ...
    //  Start the frames from Feature No. = skpF by skipping the first the features from [0  ......  K - 1] feature Vector ...
    //  And  End the processing after processing [numFeatures - K] feature Vector i.e. produce upto numFeatures - 2 * K feature 
    //  Vectors
    
     float **deltaFeatVect;
     int   iter, dimIter, kIter;
     float prevVal, nextVal;
     deltaFeatVect = (float **) calloc(numFeatures, sizeof(float *));
     //printf(" In Delta Now ....  %d Limit and NNUM_FEATURES = %d", numFeatures, numFeatures -  2 * K);
     float normVals = 0;
     
// computing the Normalizing Coefficients .... now 

     for(iter = 1; iter <= K; iter++)
              normVals += (iter * iter);


     normVals = 2 * normVals;
// Implemented the formula >>>   2 * \sum_{i=1}^{i=K} i^{2}

     for(iter = 0; iter < numFeatures; iter++){ // The Iterator is for the Input_Feature_Vector .. THE OP_FEAT_VECT should starts from [iter - K] locs
           
           deltaFeatVect[iter] =  (float *) calloc(dimFeats, sizeof(float));
           ////printf(" \n Printing the Values for iteration No = %d \n", iter - K );  
           for(dimIter = 0; dimIter < dimFeats; dimIter++){  //  Make it feel like a Vector ....   It does exactly that  ....
                   prevVal   = 0;   // For Each dimension Accumulate the MFCC values span-wise
                   nextVal   = 0;
                   
                   
                   for(kIter = 1; kIter <= K; kIter++) { //  Accumulater for the span_Variable .....  spans exactly [2 * K + 1] locations with Varying weights  
                   // Check whether the next and previous locations exists  .....
                          // check the boundary condition ....
                          if(iter + kIter < numFeatures)
                                nextVal  +=  (kIter    * inpFeatures[iter + kIter][dimIter]);
                          // check the initial part of the mfcc features .... 
                          if(iter - kIter >= 0)
                                prevVal  +=  ((-kIter) * inpFeatures[iter - kIter][dimIter]); 
                         // normVals += kIter * kIter;
                                                        }
                  
                               
                   ////printf(" %f ",  deltaFeatVect[iter - K][dimIter]);
                   deltaFeatVect[iter][dimIter] = ((nextVal + prevVal)/normVals);                               
                   ////printf(" %f ",  deltaFeatVect[iter - K][dimIter]);                                        
                                                            } // END_OF_FOR_LOOP of Variable <<< dimIter >>> 

                                                }// END_OF_FOR_LOOP of Variable <<< iter >>>  

    // //printf(" NUMBER OF ITERATION = %d\n", iteration);
     return deltaFeatVect;
    
                                                                                     }



/**
   Function Name: mfcc_computation
   Function Syntax: char * mfcc_computation(char id[], int total_no_of_frames, char fullpath_input[], int nof_fsize)
   
   Inputs:
   @param id                      - consists the id of the speaker    
   @param total_no_of_frames      - consists the total number of frames
   @param fullpath_input          - consists the fullpath of the input file name
   @param nof_fsize               - consists the number of frames obtained without overlapping, gets value only after vad module is executed

   Outputs:
   @return features_filename      - consists the Features file name where mfcc coefficients are saved. If CHANNELFLAG is 1, mfcc coefficients are saved after implementing cepstral mean subtraction
                                    

   Description : Feature extraction module calculates the Mel Frequency Cepstral Coefficients(mfcc22) for all the frames.
                 waveStruct and globDataSpeechRecgStruct have been defined in mfcc_globals.h.
*/

/*char *mfcc_computation(char id[], int total_no_of_frames, char fullpath_input[], int nof_fsize, char DirNameforMfccFeatures[]){
short *ptr;
int i,j,k=0,m=0,shift=0;
int id_len, suffix_len, features_filename_len;
char *features_filename;
FILE *fp_coeff, *fp_input;
long eoinput;
char suffix[11] = "_coeff.txt"; 
char *features_filepath;
features_filepath = (char *) calloc(SIZE_FULLPATH, sizeof(char));
features_filepath =    DirNameforMfccFeatures;   
 // Used to attach the full path to the file of coefficients without mean subtraction. PATH_TEXT - contains full path of text files directory
printf(" \n%s filepath put here \n",features_filepath);					
	
if( ( fp_input=fopen(fullpath_input,"rb") ) == NULL)  // opening input(.wav) file
  {
   printf("Cannot open the file");
   exit(0);
  }
else
  {    
     fread(&globDataSpeechRecgStruct.header,sizeof(waveStruct),1,fp_input);  //reading the header data of input(.wav) file
    
  }

       

filter_bank(globDataSpeechRecgStruct.header.Sample_Rate, globDataSpeechRecgStruct.Filter_resp); // Returns the Filter response which is a 2d matrix of 22*256 elements


ptr = (short*)malloc((FRAMESIZE * nof_fsize) * sizeof(short));    

fread(ptr, sizeof(short), nof_fsize*FRAMESIZE, fp_input);         //reading complete data as single chunk

fclose(fp_input);


/
The following piece of code is concatenating id with constant _coeff.txt to generate id_coeff.txt where the coefficients are getting saved. Then the fullpath is being attached to the filename to give the complete
path of filename.
/
id_len = strlen(id);
suffix_len = strlen(suffix);
features_filename_len = id_len + suffix_len + strlen(PATH_TEXT) + 1;
features_filename = (char*)malloc(features_filename_len * sizeof(char));

strcpy(features_filename, features_filepath);
strcat(features_filename,id);
strcat(features_filename,suffix);

printf("\nfeatures=%s\n",features_filename);

if((fp_coeff = fopen(features_filename, "w")) == NULL)         // opening id_coeff.txt file (eg: 9001_coeff.txt, 9002_coeff.txt)
		{
			printf("Unable to open the %s file\n",features_filename);
			exit(0);
		}

eoinput= nof_fsize*FRAMESIZE - FRAMESHIFT;
/
   Calculating the mel cepstral coefficients obtained after implementing 5 functions as mentioned below. 
   While Loop Logic: One temporary variable shift is used to traverse the entire file and take one frame of FRAMESIZE with shift of FRAMESHIFT. Sample value is calculated for the every frame and following 5 functions are called.
                     hamming(), DFT(), frequency_warping_spectrum(), Log_spectrum(), IDCT() through which we finally get mfcc coefficients. 
                     

          while((shift != eoinput))   
             {
                                       for(i=0; i<DFT_POINT; i++)  //calculating channel data for one block of 20 milli seconds
				        {
					      if(i<FRAMESIZE)
						  {
							globDataSpeechRecgStruct.sample_value[i] = (float)(*ptr)/(float)pow(2,(globDataSpeechRecgStruct.header.Bits_Per_Sample)-1);  
                                                         ptr++;
						  }
				              else
						  {
							globDataSpeechRecgStruct.sample_value[i] = 0;                              //assigning zero from 161 to 512 samples
						  }
	
				         }

					hamming(globDataSpeechRecgStruct.sample_value, globDataSpeechRecgStruct.hamming_output1);  //calculates the hamming output
                                        
                                        dft(globDataSpeechRecgStruct.hamming_output1, globDataSpeechRecgStruct.final_arg1);        //computes the 512 point DFT
                                        
					//frequency_warping_spectrum(globDataSpeechRecgStruct.Filter_resp, globDataSpeechRecgStruct.final_arg1, globDataSpeechRecgStruct.warping_spectrum1); //calculates the warping spectrum
                                        
					//Log_spectrum(globDataSpeechRecgStruct.warping_spectrum1, globDataSpeechRecgStruct.log_magnitude1);    //calculates the log magnitude
                                        
					//IDCT(globDataSpeechRecgStruct.warping_spectrum1, globDataSpeechRecgStruct.IDCT_Output1);                 //computes the Inverse Discrete cosine transform                      



                                        for(i=1; i<=NUM_OF_COEFFICIENTS; i++)
						{
							fprintf(fp_coeff, "%1.10f\n", globDataSpeechRecgStruct.final_arg1[i]);             //printing the mfcc coefficients to coefficients.txt file 
						} 				 

                       shift = shift + (FRAMESHIFT);                                // shift incremented by FRAMESHIFT
                       ptr = ptr - FRAMESHIFT;                                      // ptr decremented by frameshift
                       k=k+1;
                      
			

          }     // while LOOP ends

fclose(fp_coeff);

ptr = ptr - eoinput;
free(ptr);	

/**
    Cepstral Mean Subtraction Logic is implemented only if CHANNELFLAG is 1. 
*/

           /* if(CHANNELFLAG == 1)

           {
            
	     cms(total_no_of_frames,features_filename);  // calling the cepstral mean subtraction function to subtract the mean from the coefficients and save the mean subtracted coefficients in file
             
                        
           }
            
           
            return features_filename;
            

           

}   //mfcc_computation() ends here*/




/** 

   Function Name: filter_bank
   Function Syntax: void filter_bank(float sample_rate, int filter_nos, int half_dft, float Filter_resp[][DFT_POINT])
   
   Description: filter_bank calculates the frequency response of the mel filter bank
   
   Inputs:     
   @param sample rate            - consists the sampling rate of the input file
   @global NUM_OF_FILTER         - consists the number of filters constituting the filter bank. Defined in mfcc_define.h file.        
   @global DFT_POINT             - defined in mfcc_defines.h file

   Outputs:
   @param Filter_resp            - returns the Filter Coefficeints as a 2D matrix
   
   Logic : The filter bank () computes the filter frequency response on mel scale. 
           (i)   Mel frequency is calculated using the standard formula used in the filter_bank (). The standard formula is 
                 mel_frequency = 2595 * log10((1+(sample_rate/1400)))
           (ii)  The maximum frequency limit on linear scale is 4000 Hz which is equivalent to sample_rate/2 . 
           (iii) This frequency on linear scale is converted to mel scale in order to capture the vocal tract spectral information in a better way. 
           (iv)  From the mel-frequency,filter spacing is calculated for each of the 22 filters that constitute the filter bank which is then used to calculate the center frequencies.
           (v)   Now on frequency scale of 0-4000 Hz, we compute the modified bin[] which is then compared to modified_bin[bin_center] where each bin center is the center of each traingular filter. The comparision is done to find        
                 whether value of modified bin [] lies on positive slope or the negative slope of the triangular filter so that accordingly frequency response is calculated by equation y=mx where m is the slope of the triangular filter
                 and x is the value of modified_bin[].
*/

FILE *FiltBnk;
void filter_bank(float sample_rate, float Filter_resp[][DFT_POINT])      
{

        FiltBnk = fopen("Filt.txt", "a");
	float filter_spacing, mel_freq, center_freq[NUM_OF_FILTER+1], modified_bin[DFT_POINT];
	float scale_point_freq;
	int i, j;
	int bin_center[NUM_OF_FILTER+1];
        int half_dft=DFT_POINT/2;        

	mel_freq = 2595 * log10((1+(sample_rate/1400)));                                           // Calculating Mel Frequency
  
	filter_spacing = (mel_freq/(NUM_OF_FILTER+1));                                             // Calculating Filter Spacing using mel frequency

	for(i=0; i<=NUM_OF_FILTER; i++)
		{
			center_freq[i] = 700 * (pow(10,((filter_spacing * (i+1))/2595)) - 1);      // Calculating Center Frequency using filter spacing
		}

	scale_point_freq = ((float)sample_rate/2)/half_dft;                                               // Calculating slot in frequency domain

/******************************** below three FOR loops used to calculate the Filter bank Coefficents *****************************************/
	for(i=0; i<=half_dft+1; i++)
		{
			modified_bin[i] = (scale_point_freq * i);                                        // Calculating modified bin using slot             

		}

	for(i=0; i<=NUM_OF_FILTER; i++)
		{
			bin_center[i] = ((center_freq[i] * half_dft)/(sample_rate/2)) + 0.99;    // Calculating bin center using center frequency
		}
	
	bin_center[-1] = 0;
        

/**
   Calculating Filter Frequency Response
   Logic of the following loop: The following loop calculates the filter frequency response. The response is calculated by first finding whether the value of modified_bin lies on the positive slope or on the negative slope. 
   If neither of the two happens the value is assigned to 0. The frequency response is calculated individually for each of the 22 filters that constitute the filter bank.

*/
	for(i=0; i<NUM_OF_FILTER; i++)
		{
			for(j=0; j<half_dft; j++)
				{
					if((modified_bin[j] < modified_bin[bin_center[i]]) && (modified_bin[j] > modified_bin[bin_center[i-1]]))
						{
							Filter_resp[i][j] = (modified_bin[j]-modified_bin[bin_center[i-1]])/(modified_bin[bin_center[i]]-modified_bin[bin_center[i-1]]);
                                                        
						}
				    	else
						{
							if((modified_bin[j] >= modified_bin[bin_center[i]]) && (modified_bin[j] < modified_bin[bin_center[i+1]]))
							{
								Filter_resp[i][j] = (modified_bin[bin_center[i+1]]-modified_bin[j])/(modified_bin[bin_center[i+1]]-modified_bin[bin_center[i]]);
                                                                  fprintf(FiltBnk,"%f \t", Filter_resp[i][j]); 
							}			
							else
							{
								Filter_resp[i][j] = 0;
                                                                  fprintf(FiltBnk,"%f \t", Filter_resp[i][j]); 
							}
                                                        
						}

					
				}

                     
		}
         
        fclose(FiltBnk);

}	// filter_bank() ends here





/**
Function Name: hamming()
Function syntax: void hamming(float input[], float output[])

Description: Applying hamming window to speech signal

Inputs:
   @param input          - contains the channel data for one frame.

Outputs:
   @param output         - returns the windowed data.
            
   Logic: Hamming() calculates the hamming output of the channel data. It is computed by multiplying the hamming equation with the channel data for the entire length of channel data.
*/
FILE *Hamm,Hamm1;
void hamming(float input[], float output[])

{

    Hamm = fopen("Hamm.txt" , "a");
    
	int i,count_1=0;
	float w;

	for(i=0; i<DFT_POINT; i++)
		{
			w = (float)(0.54 - (0.46 * (float)cos((2*PI*i)/(FRAMESIZE-1))));  // applying hamming equation
                        //printf("HAMMING WINDOW\t%f\n",w);
                        //printf("INPUT[i]\t%f\n ",input[i]); 
			output[i] = (w * input[i]);
                        //printf("INPUT IS \t%f\n", input[i]);
                        //printf("OUTPUT[i] \t%f\t  ", output[i]);
                        fprintf(Hamm, "%f \t", output[i] );
                        count_1++;
                      
		}
       
        
        
        //printf("count 1 is \t%d\n", count_1);
        fprintf(Hamm," \n");
        fclose(Hamm);
        
        
}












/**     
Function Name: dft()
Function Syntax: void dft(float real_input[], float final_arg[])

Description: Calculating dft of windowed speech signal

Inputs:
    @param input          - input contains the hamming output of the channel data.

Outputs:
    @param final_arg      - returns the 256 point dft of the windowed data. 
            
    Logic : dft() computes the 512 point dft of the hammimg windowed output. 
            (i)   The function returns the first 256 points only taking into consideration the symmetricity property of the Discrete Fourier Transform(dft).
            (ii)  dft is computed in 2 steps. In 1st step, the values of hamming windowed output are readjusted by swapping the values. The readjustment is done to implement the bit reversal concept required in Decimation in Time 
                  FFT implementation.
            (iii) The swapped values are then used for dft calculation. Here dft is calculated by implementing the algorithm of Decimation in time(DIT) FFT i.e 512 point is computed by computing first 1-point, 2-point,4-point, 8-        
                  point ..... and finally 256-point.
            (iv)  After getting all the values the magnitude at each point is calculated by taking the sqaure root of sum of square of real part and imaginary part. Finally the 1st 256 points are returned in the array final_array[].
*/

FILE *dft_compute;
void dft(float real_input[], float final_arg[])
{
       
    dft_compute = fopen("DFT_compute.txt", "a");
	int n,m;
	double complex_input[DFT_POINT], arg_output[DFT_POINT];   
	int i,j,k,num1,num2,half_dft;
	double c,s,e,a,temp_var,butterfly_real,butterfly_complex;                              // c-cosine, s-sine, e-exponential, a-angle


	n = DFT_POINT;
	m = (int)(log(n)/log(2));
	j = 0; 
	half_dft = n/2;

	for(i=0; i<DFT_POINT; i++)
		{
			complex_input[i] = 0;   //Intializing the array to zero.
		}

	
/**
    The following loop is implementing the concept of bit-reversal of each element's index so that the new sets of inputs can be used in the Decimation in Time concept of FFT. Bit reversal of each element's index is found
    and swapping is done for the value of original index and the value of bit reversed index. Eg: a[x] is the element, x is bit reversed which gives us y so a[x] swapped with a[y]
*/
          for (i=1; i<(n-1); i++)              // loop is for readjusting the values of hamming output to implement FFT
		{
			num1 = half_dft;
	
			while (j >= num1)
				{
					j = j - num1;
					num1 = num1/2;
				}
	
			j = j + num1;
	
			if (i < j)                                 // this block is used to swap the values to implement FFT
				{
					temp_var = real_input[i];
					real_input[i] = real_input[j];
					real_input[j] = (float)temp_var;
					/*
                                          temp_var = complex_input[i];
					  complex_input[i] = complex_input[j];        // written the code just to demonstrate the complex part of the input should be swapped to implement FFT
					  complex_input[j] = temp_var;                // as complex part is 0 here we are not performing this operation.
                                        */
				}
		}

	num1 = 0; 
	num2 = 1;

/**

*/
	for (i=0; i < m; i++)                         // To calculate FFT's from the swapped inputs
		{
			num1 = num2;
			num2 = num2 + num2;
			e = -6.283185307179586/num2;
			a = 0.0;

			for (j=0; j < num1; j++)        
				{
					c = cos(a);
					s = sin(a);
					a = a + e;

					for (k=j; k < n; k=k+num2)
						{
							butterfly_real = c*real_input[k+num1] - s*complex_input[k+num1];
							butterfly_complex = s*real_input[k+num1] + c*complex_input[k+num1];
							real_input[k+num1] = real_input[k] - (float)butterfly_real;
							complex_input[k+num1] = complex_input[k] - (float)butterfly_complex;
							real_input[k] = real_input[k] + (float)butterfly_real;
							complex_input[k] = complex_input[k] + butterfly_complex;
						}
				}
		}


	for(i=0;i<n;i++)                 // calculating the magnitude of complex FFT
		{
			arg_output[i] = sqrt(pow(real_input[i], 2) + pow(complex_input[i], 2));
                        //printf("DFT IS\t%f\n",arg_output[i]);
		}

		
	for(i=0; i<(DFT_POINT/2); i++)  // storing the values for first 256 points
		{
			final_arg[i] = (float)arg_output[i];
                        //printf("DFT IS\t%f\n",final_arg[i]);
                        fprintf(dft_compute, " %f \t", final_arg[i]);   

		}
        fprintf(dft_compute," \n");
        fclose(dft_compute);

}	// dft() ends here




/**
Function Name: frequency_warping_spectrum()
Function Syntax: void frequency_warping_spectrum(float Filter_resp[][DFT_POINT], float dft_value[], float spectrum_out[])

Description: Computing the frequency warped spectrum by passing the DFT output through the filter bank.

Inputs:
@param Filter_resp   - consists the 2D matrix of filter response.
@param dft_value     - consists the 256-point DFT of the windowed data

Outputs:
@param spectrum_out  - returns the filter warped spectrum of the input data.
            
            Logic : frequency_warping_spectrum() calculates the frequency warped spectrum to map the Discrete fourier Transform of the windowed data onto mel scale.
                    (i)  It is computed by multiplying the filter response of the triangular filter which is a 2D matrix of 22*256 elements with the 256 point DFT of the windowed data.
                    (ii) The result of multiplication is strored in an array spectrum_out[] which is returned as the final output. The values of array spectrum_out[] are mapped spectrum onto mel scale.
*/
FILE *fp; 

void frequency_warping_spectrum(float Filter_resp[][DFT_POINT], float dft_value[], float spectrum_out[])

{
     
	fp = fopen("1.txt","a");
	int i, j;

	for(i=0; i<NUM_OF_FILTER; i++)					
		{
			spectrum_out[i] = 0;

			for(j=0; j<(DFT_POINT/2); j++)			
				{
					spectrum_out[i] = spectrum_out[i] + Filter_resp[i][j] * dft_value[j] ;   // DFT values being warped with filter response
					
				}
                        fprintf(fp, "%f \t", spectrum_out[i] );
                        //printf("FREQUENCY_SPECTRUM IS\t%f\n",spectrum_out[i]);
                        
                        
		}
         fprintf(fp," \n" );
fclose(fp);    	
	
}	//frequency_warping_spectrum() ends here


  
/** 
Function Name: Log_spectrum
Function Syntax: void Log_spectrum(float spectrum_output[], float log_out[])

Description: Applying Log function on frequency warped spectrum which is the output of frequency_warping_spectrum ()

Inputs:
@param spectrum_output  - consists the frequency warped spectrum on mel scale.

Outputs:
@param log_out          - returns the log values of the frequency warped spectrum.


               Logic : Log_spectrum () computes the logarithm of the frequency warped spectrum on mel scale and returns the values in an array log_out[].

*/

FILE *fp1; 
void Log_spectrum(float spectrum_output[], float log_out[])

{
   
        fp1 = fopen("2.txt","a");
	int i,count=0; 
        float Log_var[FRAMESIZE][NUM_OF_FILTER];
        
       

	for(i=0; i<NUM_OF_FILTER; i++)
		{
			log_out[i] = (float)log(spectrum_output[i])/(float)log(10);  // taking the logarithm of frequency warped spectrum 
			//log_out[i] = (spectrum_output[i])/10.0;
			//printf("\tlog=%f\t", log_out[i]);
                       
                        fprintf(fp1 ,"%f \t", log_out[i] );
                        //printf("\n%d", i);
                        count++;
		}
        //printf("count is\t%d\n",count);
        //printf("\nlog=%f\n", log_out[1]);
        //fscanf(fp1, " %d \n", &Log_var[i]);
       // fprintf()
        
       
        fprintf(fp1," \n" );
        //fclose (fp1);
        fflush(fp1);
        
        

        
        
}

        
	//Log_spectrum() ends here

FILE *myfile1;
FILE *myfile2, *MATRIX1,*MATRIX2, *transpose_1,*transpose_2,*transpose_3, *target_A, *mean_in_KAKA,*mean_in_PAPA,*mean_in_TATA,*mean_in_tata,*malo_1T,*twoDDCT_IN,*covar_in, *malo_2I,*DFT_IN,*check_NW1,*check_NW2,*check_NW3;
void twoDimensional_DCT(float log_out[], float A_out[], int totalFrames)
{
   
    myfile1 = fopen("conn_log_Spectrum.txt","r");
    MATRIX1 = fopen("input_weight_C1.txt","r");
    MATRIX2 = fopen("input_weight_C2.txt","r");
    myfile2 = fopen("conn_Patching.txt","w");    
    transpose_1 = fopen("transposed_C1.txt","w");
    transpose_2 = fopen("conn_patching_inter.txt","w");
    transpose_3 = fopen("out_2DDCT.txt","w");
    malo_1T = fopen ("out_(C-M).txt","w");
    target_A = fopen("A_111.txt","r");
    mean_in_KAKA = fopen("input_mean_k.txt", "r");
    mean_in_PAPA = fopen("input_mean_p.txt", "r");
    mean_in_TATA = fopen("input_mean_T1.txt","r");                                      ///////////THE MEANs//////////////
    mean_in_tata = fopen("input_mean_t.txt", "r");
    twoDDCT_IN = fopen("C.txt","r");
    covar_in = fopen("cov_NEW.txt", "r");
    malo_2I = fopen("(C-M).txt", "r");
    DFT_IN = fopen ("DFT_compute.txt", "r");
    check_NW1 = fopen ("TEST1.txt", "w");
    check_NW2 = fopen ("TEST2.txt", "w");
    check_NW3 = fopen ("TEST3.txt", "w");
    //float DFT_transpose[250][950];
    
    int k,l,m,i,j,p,q,r,a,b,c,d,e,f,g,h,p1,r1,x,y,x1,y1,f1,f2;
    int rslt;
    float arr1[totalFrames];
    float NEW[totalFrames][NUM_OF_FILTER];
    float NEW_patching_1[totalFrames][100];
    //float NEW_patching_2[NUM_OF_FILTER][totalFrames];
    //float Arrey_out[6][NUM_OF_FILTER];
    float matrix1[60][60];
    float matrix2[3][60];
    float transpose_C1[22][9];
    float multiply1[70][70];
    //float transpose_C2[60][60];
    //float transpose_C3[60][60];
    float multiply2[70][70];
    float mult_NEW[30][5];
    float mult_new[70][70],covar_mult[1][27],covar_mult1[1][1];
    float malo1_transpose[1][27];
    float A_tar[60][60];
    float mean[27][1],covariance[28][28],substract_transpose[27][1];
    float TWOddct[27][1];
    float sum=0,sum1=0,sum2=0,sum3=0,sum4=0;
    float malo_1[27][1]; /*malo_2[27][27],malo_3[27][27]*/
    
    
    
    float DFT_in[1000][300];
    float DFT_transpose[39][950];
    float DFT_avg[39][950];
    
    float b1,b2;
    float fs=16000;
    int i1,j1,i2,j2;
    float sum_last=0; float avg_last=0;
    
    //char choice = '2';
    
    
         for(int i=0; i<totalFrames; i++)
                {
                     arr1[i]=0;
                     //printf("ARR1 \t%f\n", arr1[i]);
                     //printf("COUNT \t%d\n", i);
    
                }
    float     diff;
    
    for(int k=1; k<totalFrames; k++)
    {
         {
             
             arr1[k]= arr1[k-1]+FRAMESHIFT;
            //printf("ARRAY G IS \t%f\n",arr1[k]);
             
         }
             
            
             float p=7800;
             diff = (arr1[k]-p);
             if(diff>0 && diff<16)
             {
                 rslt =k;
                 //printf("\t\t\t\tDIFF IS \t %d \n", k);
             }
    }        
    //printf("\t\t\t\tDIFF IS \t %d \n",rslt);
    for(int m=0; m<totalFrames-2 ; m++)
    {
    for(l=0; l<NUM_OF_FILTER; l++)
    {
        fscanf(myfile1, "%f", &NEW[m][l]);
       //printf(" The array is \t%f\n ", NEW[m][l]);
    }
   
    }
    
    
    for(int i=rslt; i<rslt+60; i++)
    {
        for(int j=0; j<NUM_OF_FILTER; j++)
        {
            
            //printf("THE FINAL ARRAY IS \t%f\n", NEW[i][j]);                    /////////////PATCHING output  (NEW[i][j]) //////////
            fprintf(myfile2 , "%f\t", NEW[i][j]);
            NEW_patching_1[i][j]=NEW[i][j];
            //printf("RESULT NOW IS----->> %f\t\t\t ",NEW_patching_1[i][j]);
        }
         fprintf(myfile2, "\r\n");
         
    }
    
    
    /*////////////////////////////////////////////////////////
    for(int p1=rslt;p1<rslt+60;p1++){
        for(int r1=0;r1<NUM_OF_FILTER;r1++){
            
            NEW_patching_1[p1][r1]=NEW[p1][r1];
            //printf("RESULT NOW IS----->> %f\t\t\t ",NEW_patching_1[p1][r1]);
        }
        
   }
    
    for(int p1=0;p1<NUM_OF_FILTER;p1++){
        for(int r1=rslt; r1<rslt+60; r1++){
            
           //printf("RESULT NOW IS----->> %f\t\t\t ",NEW_patching_1[p1][r1]);
        }
        
   }
    ///////////////////////////////////////////////////////////*/
    
    for(int i=0;i<9;i++)
    {
        for(int j=0;j<22;j++)
        {
           fscanf( MATRIX1, "%f", &matrix1[i][j]);                               //////// (C1.txt) is transposed////////
           //fscanf( MATRIX2, "%f", &matrix2[i][j]);
           //printf("matrix1@ is \t%f\t   ", matrix1[i][j]);
           transpose_C1[j][i]=matrix1[i][j];
           //transpose_C2[j][i]=matrix2[i][j];
           //printf(" matrix1 is \t%f   ", transpose_C1[j][i]);
           //printf("\n\n\n matrix2  is \t%f   ", transpose_C2[j][i]);
           
        }
       
    }
    
    for(int i1=0;i1<3;i1++)
    {
        for(int j1=0;j1<60;j1++)
        {
                                                                                 ////////// (C2.txt)///////////
           fscanf(MATRIX2, "%f", &matrix2[i1][j1]);
        }}
    
    
    /*for(int p1=0;p1<60;p1++){
        for(int r1=0;r1<22;r1++){
            fscanf( target_A, "%f", &A_tar[p1][r1]);                              /////////Copying A_out(PATCHING.txt) && putting in A_111/////////
            //printf("NEW is %f\t\t  ", A_tar[p1][r1]);
        }
        
   }*/
    
    
   
    for(int i=0;i<22;i++)
    {
        for(int j=0;j<9;j++)
        {
            fprintf(transpose_1, "%f\t", transpose_C1[i][j] );                    ///////////(C1.txt) transpose, written to a file////////
            //printf("NEW is %f\n  ", transpose_C1[i][j]);
        }
    
    fprintf(transpose_1, "\r\n");
     }
    
    
    
    for(int p=rslt,a=0;p<rslt+60,a<60;p++,a++){
        for(int q=0;q<9;q++){
            for(int r=0;r<NUM_OF_FILTER;r++){
                
               sum= sum+ (NEW_patching_1[p][r]*transpose_C1[r][q]);
               //printf("RESULT NOW IS----->> %f\t\t\t ",NEW_patching_1[p][r]);
               //printf("The result is--->--->>%f\t\t\t",sum);
            
            }
            ///////////////////////////////NOW THIS?????????  ON THIS////////////
            //for(int a=0;a<60;a++){
                   //for(int b=0;b<9;b++){                                               /////////////MATRIX MULTIPLY///////////////
                       
                       multiply1[a][q]=sum;
                      
                     //printf("The result is----->--->>  %f\t\t ",multiply1[a][q]);
                      fprintf(transpose_2, "%f\t",multiply1[a][q] );
                      //q++;
                      //break;
                   // }
              //break;
            //}
                      sum=0;
                      continue;
            }
            
        //}
        fprintf(transpose_2, "\r\n");
      //printf("NEW is %f  ", A_tar[p][r] );
    }
    
    
    for(int f=0;f<60;f++){
        for(int g=0;g<9;g++){
            //printf("The result is  %f\t\t\t ",multiply1[f][g]);
            //fprintf(transpose_2, "%f\t",multiply1[f][g] );
        }
         //fprintf(transpose_2, "\r\n");
    }    
    
    
    
    
    for(c=0;c<3;c++){
        for(d=0;d<9;d++){
            for(e=0;e<60;e++){
                //printf("The result is---->----->>  %f\t\t\t ",multiply1[e][d]);
                sum1= sum1+ (matrix2[c][e]*multiply1[e][d]);                           
                 //printf("sum is %f \n ", matrix2[c][e]);
            }
           
            multiply2[c][d]=sum1;
            sum1=0;
          //printf("The result is  %f\t",multiply2[c][d]);
        }
    }
    
    ////////////////***************FINAL OUTPUT OF 2DDCT***********//////////////////
    
    for(int f=0,i=0;f<3,i<27;f++,i++){
        for(int g=0;g<=8;g++){
             mult_new[g][f]= multiply2[f][g];   
             //printf("ans is %f\n", mult_new[g][f]);
            fprintf(transpose_3, "%f\t",multiply2[f][g]);                        /////////2DDCT output (multiply2[][]) && T_3.txt/////////////
            fprintf(transpose_3, "\r\n");
            //printf("ans (2DDCT Final check------>>) is %f\n", multiply2[f][g]);  /////////Automatic upto here////////
            
            
                for(int j=0;j<1;j++){
                mult_NEW[i][j]= multiply2[f][g];
                
                //printf("ans (2DDCT Final check------>>) is %f\n",  mult_NEW[i][j]);
                 }
            if (g==8){
                //printf("thissssssssss");
                break;}
            else{ i++;
                      }
        }
        //i=0;
    }
    
                    ////////////////////////NOW THIS again///////////////////
    for(int f1=0;f1<27;f1++){
        for(int g1=0;g1<1;g1++){
          //printf("ans is %f\n", mult_NEW[f1][g1]);
          
        }
    }
             
    
    
    for(int x=0;x<27;x++){
        for(int y=0;y<1;y++){
               mult_new[x][y]= multiply2[x][y];
               //printf("mean is %f\n",multiply2[x][y]); 
               //fprintf(malo_1T, "%f\t",multiply2[x][y]);
        }
    }
            
            
            
            
     for(x=0;x<27;x++){
        for(y=0;y<1;y++){
           fscanf(mean_in_PAPA," %f", &mean[x][y]);
          //printf("mean is %f\n",mean[x][y]); 
           }
       }
         //fprintf(transpose_3, "\r\n");
       
   ////////////////////////////**************(C-M)****************/////////////////////////
    
    for(x1=0;x1<27;x1++){
        for(y1=0;y1<1;y1++){                                                            //////////Copy T_3.txt to C.txt for "C" matrix////////
            fscanf(twoDDCT_IN," %f", &TWOddct[x1][y1]);                                  //////////T_3.txt is the 2DDCT matrix/////////
             //printf("malo_1 is %f\n", TWOddct[x1][y1]);                                          
            malo_1[x1][y1] = mult_NEW[x1][y1] - mean[x1][y1];                           ///////////(C-M) calculation
            //printf(" malo_1 is\t %f\n", malo_1[x1][y1]); 
            malo1_transpose[y1][x1] = malo_1[x1][y1];
            
        }
         
    }
    
    for(int x2=0;x2<1;x2++){
        for(int y2=0;y2<27;y2++){
            
             //printf("\n\n malo_transpose is\t %f\n", malo1_transpose[x2][y2]);     ///////(C-M) matrix to txt file T_4.txt i.e [T_4.txt is (C-M)]////////
            
             fprintf(malo_1T, "%f\t",malo1_transpose[x2][y2]);
             fprintf(malo_1T, "\r\n");
        }
          
      }
    
    for(int i=0;i<27;i++){
        for(int j=0;j<27;j++){
             fscanf(covar_in," %f", &covariance[i][j]);                              /////READ the covariance matrix from txt file.
             //printf("\n covariance matrix is\t %f\n", covariance[i][j]);
        }
    }
    
    ///////////////////************(C-M)'*ll*(C-M)************/////////////////////
    
    for(int f1=0;f1<1;f1++){
        for(int g1=0;g1<27;g1++){
            for(int h1=0;h1<27;h1++){
                sum2= sum2 + (malo1_transpose[f1][h1]*covariance[h1][g1]);            /////////(C-M)'*ll//////////
                 //printf("sum is %f \n ",malo1_transpose[f1][h1]);
            }
           
           covar_mult[f1][g1]=sum2;
           sum2=0;
        
           //printf("The result is  %f\t\n",covar_mult[f1][g1]);
           fscanf(malo_2I," %f", &substract_transpose[g1][f1]); 
           printf("The result is  %f\t\n", substract_transpose[g1][f1]);      
        }
    
           for(int f2=0;f2<1;f2++){
                for(int g2=0;g2<1;g2++){
                     for(int h2=0;h2<27;h2++){
                         sum3= sum3 + (covar_mult[f2][h2]*malo_1[h2][g2]);        ///////////*(C-M)//////////
                         //printf("The result is---->----->>%f\t\n",mult_NEW[h2][g2]);
                     }
                     covar_mult1[f2][g2]=sum3;
                     sum3=0;
                     printf("%f", covar_mult1[f2][g2]);            /////////Final output = (covar_mult1[f2][g2]) MAHALNOBIS DISTANCE/////////
        }
    }
        
    }
    
                        //////////////////////AUTOMATIC selection///////////////
    
    {
    for(i1=0; i1<=(totalFrames-3); i1++)
    {
       for(j1=0; j1<=255; j1++)
       {
        fscanf(DFT_IN, "%f", &DFT_in[i1][j1]);                                      ///////////DFT matrix///////////
        //DFT_Transpose[i1][j1]= DFT_in[j1][i1];
       
        //printf("DFT is %f \t\n", DFT_in[i1][j1]);
        fprintf(check_NW1, "%f \t", DFT_in[i1][j1]);
        
       }
      fprintf(check_NW1,"\r\n");
    }
    //printf("\n\n");
    
    
    }
    
    for(int i=0; i<=255; i++)
    {
       for(int j=0; j<=(totalFrames-3); j++)
       {
         //printf("DFT is %f \t\n", DFT_transpose[i][j]);
         //fprintf(check_NW1, "%f \t", DFT_in[i][j]);
         
       }
        fprintf(check_NW1,"\r\n");
    }                                             
    
    
    b1 = ((500*DFT_POINT)/fs);
    //printf("f1 is %f \n\n", b1);
    b2 = ((1700*DFT_POINT)/fs);                                                 ///////////F1 and F2 frequency band points//////
    //printf("f2 is %f \n\n", b2);
    
    
    //for(int p=0; p<=(totalFrames-3); p++)
        
    {  
        i2=i2++;
        for(i2=0; i2<=(totalFrames-3); i2++)
        {
            for(j2=b1; j2<=b2; j2++)
            {
              //printf("NEW DFT is %f \t", DFT_in[i2][j2]);                     //////////Wrapping the DFT matrix with F1 nd F2 and taking the AVERAGE/////
              sum_last=sum_last+DFT_in[i2][j2];
              fprintf(check_NW2, "%f \t", DFT_in[i2][j2]);
              //fscanf(check_NW2, "%f\t", &DFT_avg[i2][j2]);
              //printf("NEW DFT is %f \t", DFT_avg[i2][j2]);  
            }
            avg_last=sum_last/39;
            sum_last=0;
            //printf("AVERAGE IS %f\n\t ", avg_last);
            fprintf(check_NW3, "%f \t", avg_last);
            fprintf(check_NW2,"\r\n");
            fprintf(check_NW3,"\r\n");
            i2=i2++;                                                            ///////AVERAGE of all the wrapped DFTs///////////
            //break;
        }
        i2=i2++;
        fprintf(check_NW2,"\r\n");
        
    }
    //i2=i2++;
    //}
    
    
    
        
    
    
    /*switch(choice){
        case '1':
             for(int f2=0;f2<1;f2++){  
        for(int g2=0;g2<27;g2++){
            for(int h2=0;h2<27;h2++){
                sum4= sum4 + (malo1_transpose[f2][h2]*covariance[h2][g2]);
                 //printf("sum is %f \n ",covariance[h1][g1]);
            }
           
           covar_mult[f2][g2]=sum4;
           sum4=0;
        
           printf("The result is(NOW)  %f\t",covar_mult[f2][g2]);
        
    }
             }
        case '2':
            printf(" INVALID request");
    }*/
    
  
    
    
    
    
    
     
         fclose(myfile1);
         fclose(myfile2);
         fclose(transpose_1);
         fclose(transpose_2);
         fclose(transpose_3);
           //fclose(covar_in);
    
} 

////////////////*****************MAHALNOBIS DISTANCE****************////////////////////////

//FILE *covar_in;
FILE *myfile2;
void automatic_selection(float mahal_out[],char *x,int totalFrames)
{ int d=0;
    printf("i am here %d=",d);
    myfile2= fopen("TEST.txt","w");
    //printf("\nInside \n");
 //   char *articulation="";
 //   *articulation=*x;
    //printf(" 1st---->%s\t ",x);
    
    printf("cccccccccc");
     switch(*x){
         case 'k':
             printf("Printed for K input.");
             twoDimensional_DCT(globDataSpeechRecgStruct.log_magnitude1,globDataSpeechRecgStruct.A_out, totalFrames ); 
             //printf("Printed for K input.");
             
             
            break;
         case 't':
             twoDimensional_DCT(globDataSpeechRecgStruct.log_magnitude1,globDataSpeechRecgStruct.A_out, totalFrames ); 
              printf("Printed for t input.");
            break;
   
         case 'p':
             twoDimensional_DCT(globDataSpeechRecgStruct.log_magnitude1,globDataSpeechRecgStruct.A_out, totalFrames ); 
              printf("Printed for p input.");
             //printf("THIS is P");
         //fprintf(myfile2,"%d",10);
            //printf (myfile2,"this is line %d\n",12);
                    
            break ;
         case 'T':
             twoDimensional_DCT(globDataSpeechRecgStruct.log_magnitude1,globDataSpeechRecgStruct.A_out, totalFrames ); 
              printf("Printed for T input.");
              //printf(" \n T is printed");
            break;
           
            
         case 2:
            //printf(" INVALID request");
            break;
         default:
             //printf(" default value printed");
             break;
    }
    
/*
    //covar_in = fopen("cov_k.txt", "r");T x before use

    int f,g,h,i;
    double covariance[27][27];
    for(f=0;f<27;f++){
        for(g=0;g<27;g++){
            //fscanf(covar_in, "%f", &covariance[f][g]);
          
        }
          
   }
     for(h=0;h<27;h++){
        for( i=0;i<27;i++){
            //printf(" covariance is %f \n", covariance[h][i]);
        }
     }
     //fclose(covar_in);
    */
}     



FILE *melENERGY, *intel_A_out,*first_product,*C1_matrix,*C2_matrix,*second_product;
void intelligibility(float log_out[], int totalFrames)
{
    melENERGY =fopen("2.txt", "r+");
    intel_A_out= fopen("A_intel", "w+");
    first_product=fopen("1_p_intel", "w");
    second_product=fopen("2_p_intel.txt","w");
    C1_matrix=fopen("C1_intel.txt","r");
    C2_matrix=fopen("C2_intel.txt","r");
    
    
    float mel_LOG[totalFrames][NUM_OF_FILTER];
    float A_out[totalFrames][NUM_OF_FILTER];
    float matrix1[13][22];
    float matrix2[4][50];
    float transpose_C1[22][13];
    float sum_1=0,sum_2=0;
    float multiply1[60][30];
    float multiply2[4][13];
    
    
    
    for(int i=0;i<totalFrames;i++){
        for( int j=0;j<NUM_OF_FILTER;j++){
             fscanf(melENERGY, "%f", &mel_LOG[i][j]); 
        }
    }
    
    for(int i=(totalFrames-57);i<totalFrames-7;i++){
        for( int j=0;j<NUM_OF_FILTER;j++){
            A_out[i][j]=mel_LOG[i][j];
            fprintf(intel_A_out, "%f\t", mel_LOG[i][j]);
            //printf("A_out matrix is \t%f\n", A_out[i][j]);
        }
        fprintf(intel_A_out, "\r\n");
    }
    
    for(int i=0;i<13;i++)
    {
        for(int j=0;j<22;j++)
        {
           fscanf( C1_matrix, "%f", &matrix1[i][j]);                               //////// (C1.txt) is transposed////////
           //fscanf( MATRIX2, "%f", &matrix2[i][j]);
           //printf("matrix1@ is \t%f\t   ", matrix1[i][j]);
           transpose_C1[j][i]=matrix1[i][j];
           //transpose_C2[j][i]=matrix2[i][j];
           //printf(" matrix1 is \t%f   ", transpose_C1[j][i]);
           //printf("\n\n\n matrix2  is \t%f   ", transpose_C2[j][i]);
           
        }
       
    }
    
     for(int p=(totalFrames-57),a=0;p<(totalFrames-7),a<50;p++,a++){
        for(int q=0;q<13;q++){
            for(int r=0;r<NUM_OF_FILTER;r++){
                
               sum_1= sum_1+ (A_out[p][r]*transpose_C1[r][q]);
               //printf("RESULT NOW IS----->> %f\t\t\t ",transpose_C1[r][q]);
               //printf("The result is--->--->> %f\t\t\t",sum_1);
            
            }
            ///////////////////////////////NOW THIS?????????  ON THIS////////////
            //for(int a=0;a<60;a++){
                   //for(int b=0;b<9;b++){
                       
                       multiply1[a][q]=sum_1;
                      
                     //printf("The result is----->--->>  %f\t\t ",multiply1[a][q]); 
                      fprintf(first_product, "%f\t",multiply1[a][q] );
                      //q++;
                      //break;
                   // }
              //break;
            //}
                      sum_1=0;
                      continue;
            }
            
        //}
        fprintf(first_product, "\r\n");
      //printf("NEW is %f  ", A_tar[p][r] );
    }
    
    
    for(int i=0;i<4;i++){
        for(int j=0;j<50;j++){
            fscanf( C2_matrix, "%f", &matrix2[i][j]);
        }
    }
    
    
        for(int c=0;c<4;c++){
        for(int d=0;d<13;d++){
            for(int e=0;e<50;e++){
                //printf("The result is---->----->>  %f\t\t\t ",multiply1[e][d]);
                sum_2= sum_2+ (matrix2[c][e]*multiply1[e][d]);                           
                 printf("sum is %f \n ", matrix2[c][e]);
            }
           
            multiply2[c][d]=sum_2;;
            sum_2=0;
            fprintf(second_product,"%f\n", multiply2[c][d]); 
          //printf("The result is  %f\t",multiply2[c][d]);
        }
        //fprintf(second_product, "\r\n");
    }
    
    
    
    
    
    
}
 
/**              
Function Name: IDCT()
Function Syntax: void IDCT(float log_values[], float IDCT_out[])

Description: Calculating Inverse discrete cosine transform of the log values of frequency warped spectrum.

Inputs:
   @param log_values     - consists the log values of the frequency warped spectrum which is the output of Log_spectrum () .
   @global NUM_OF_FILTER - consists the number of filters constituting the filter bank. Defined in mfcc_defines.h file.

Outputs:
   @param IDCT_out    - returns the Inverse Discrete Transform as an array.

        Logic : IDCT() computes the inverse discrete transform of the log values of frequency warped spectrum. 
                (i)  IDCT is calculated by multiplying the cosine function with the log values of frequency warped spectrum.
                (ii) For each speech frame, we get an array of 13 mfcc coefficients which is returned as IDCT_out []. 
 
*/

void IDCT(float log_values[], float IDCT_out[])

{


	int i, j;

	for(i=0; i<=NUM_OF_COEFFICIENTS; i++) 
		{
			IDCT_out[i] = 0;

			for(j=0; j<NUM_OF_FILTER; j++)
				{
					IDCT_out[i] = IDCT_out[i] + (log_values[j] * (float)cos((PI * (j+0.5) * (i+1))/NUM_OF_FILTER));	// computing the inverse discrete transform of the log values of frequency warped spectrum
         																
		                }

                 }
}//IDCT() ends here




/** 
Function Name: cms()
Function Syntax: void  cms(int total_no_of_frames,  char *features_filename)

Description: Calculating the cepstral mean subtracted features and saving the coefficients in a file id_coeff.txt.

Inputs:
   @param total_no_of_frames   - consists the total number of speech frames.
   @param features_filename    - consists the filename where the mfcc coefficients are saved. cms is implmented on this file and the mean subtracted coefficients are saved on this file which is returned as an argument.
   



     Logic : cms() function generates the cepstral mean subtracted coefficients.
             
             (i)   coefficients are read from the file id_coeff.txt into a 2d array, then cumulative sum is calculated along the rows of mfcc coefficients and stored in 1d array csum_coeff[].
             (ii)  Mean is calculated from the cumulative sum and stored in 1d array mean_coeff[].
             (iii) Mean is subtracted from the coefficients to generate the cepstral mean subtracted coefficients
             (iv)  Opening the same id_coeff.txt (eg: 9003_coeff.txt) file in write mode and the final coefficients are being saved.



*/
void  cms(float **mfccCoeffs, int totalFrames,int numCoeffs){

       float *meanVects = (float *) calloc(numCoeffs, sizeof(float));
       int iter, dimIter;
       // Compute the mean for each of the dimension and then subtract it from the feature vectors .... 
       // \bm{c}^{'} = \sigma_{0}^{numFrames-1} (\bm{c_i} - \bm{c_mean}) // This equation is implemented in the next few lines ... 
       for(dimIter = 0; dimIter < numCoeffs; dimIter++) {
            meanVects[dimIter]  = 0;          
            for(iter = 0 ; iter < totalFrames; iter++)
                    meanVects[dimIter] += mfccCoeffs[iter][dimIter];
             meanVects[dimIter] =  meanVects[dimIter]/totalFrames;
                                                        } // END_OF_THE_FOR_LOOP_OF Variable <<<< dimIter>>>>> 

                  
     // Do the Subtraction Now ..... 
          for(dimIter = 0; dimIter < numCoeffs; dimIter++) {
                   
            for(iter = 0 ; iter < numCoeffs; iter++)
                   mfccCoeffs[iter][dimIter] = mfccCoeffs[iter][dimIter] - meanVects[dimIter] ;
                                                        } // END_OF_THE_FOR_LOOP_OF Variable <<<< dimIter>>>>> 

                  
	free(meanVects);

                                                          } // END_OF_THE_FUNCTION_CMS 
	







void  ZeroMeanUnitVariance(float **mfccCoeffs, int totalFrames,int numCoeffs){

       float *meanVects = (float *) calloc(numCoeffs, sizeof(float)); // store the mean of each of the dimension
       float *variance  = (float *) calloc(numCoeffs, sizeof(float)); // store the variance of each of the dimension 
       
       int iter, dimIter;
       float tmpxSquared = 0;
       // Compute the mean for each of the dimension and then subtract it from the feature vectors .... 
       // \bm{c}^{'} = \sigma_{0}^{numFrames-1} (\bm{c_i} - \bm{c_mean}) // This equation is implemented in the next few lines ...
       //  
       for(dimIter = 0; dimIter < numCoeffs; dimIter++) {
            meanVects[dimIter]  = 0;
            tmpxSquared = 0;          
            for(iter = 0 ; iter < totalFrames; iter++){
                    meanVects[dimIter] += mfccCoeffs[iter][dimIter];
                    //tmpxSquared += (mfccCoeffs[iter][dimIter] * mfccCoeffs[iter][dimIter]);
                                                       }
             
            //printf(" Before Mean = %f\n ", meanVects[dimIter]);
            meanVects[dimIter] =  meanVects[dimIter]/totalFrames;
           // variance[dimIter]  =  sqrt((tmpxSquared - (meanVects[dimIter] * meanVects[dimIter])) * 1.0/totalFrames);
            //printf("\n Variance = %f ",   variance[dimIter]);     
                                                        } // END_OF_THE_FOR_LOOP_OF Variable <<<< dimIter>>>>> 

       //  Do the Variance Computation Now .... 
         for(dimIter = 0; dimIter < numCoeffs; dimIter++) {
            
            tmpxSquared = 0;          
            for(iter = 0 ; iter < totalFrames; iter++){
                   // meanVects[dimIter] += mfccCoeffs[iter][dimIter];
                    tmpxSquared += (mfccCoeffs[iter][dimIter] - meanVects[dimIter] ) * (mfccCoeffs[iter][dimIter] - meanVects[dimIter] );
                                                       }
             
             variance[dimIter] = sqrt ((tmpxSquared  * 1.0)/totalFrames);
            
           // variance[dimIter]  =  sqrt((tmpxSquared - (meanVects[dimIter] * meanVects[dimIter])) * 1.0/totalFrames);
             //printf("\n Variance = %f Mean = %f  Number of Frames = %d ",   variance[dimIter], meanVects[dimIter], totalFrames);     
                                                        } // END_OF_THE_FOR_LOOP_OF Variable <<<< dimIter>>>>> 

                  
     // Do the Subtraction Now ..... 
           for(dimIter = 0; dimIter < numCoeffs; dimIter++) {
                   
            for(iter = 0 ; iter < numCoeffs; iter++)
                   mfccCoeffs[iter][dimIter] = (mfccCoeffs[iter][dimIter] - meanVects[dimIter])/variance[dimIter];
            
                                                        } // END_OF_THE_FOR_LOOP_OF Variable <<<< dimIter>>>>> 

                  
	free(meanVects);
        free(variance);

                                                          } // END_OF_THE_FUNCTION_CMS 


/**
              
           JOB:  Implementation of Mel Frequency Cepstral Coefficients(MFCC) computation

            a) The module is computing the mel frequency cepstral coefficients(mfcc) which are getting saved in a file id_coeff.txt (eg: 9003_coeff.txt)
            b) The module is making the use of 7 functions which are mentioned as below
               1. filter_bank()  2. hamming()  3. DFT() 4. frequency_warping_spectrum() 5. Log_spectrum() 6. IDCT() 7. cms()
               The logic of each of the above mentioned 7 functions have been explained just before the definition of each function.
            c) The first 6 functions are involved in computing the mfcc coefficients and getting it stored in array of size total_no_of_frames * NUM_OF_COEFFICIENTS. 
            d) cms() is implemented only if CHANNELFLAG is 1. If it is 0 mfcc coefficients are saved in file id_coeff.txt without implementing cms().
            d) cms() function is used to remove the channel effects from the coefficients so that coefficients will represent only the vocal tract information.
               cms() subtacts the mean of the coefficients from each of the coefficients and hence finally we get the cepstral mean subtracted coefficients which are written to file id_coeff.txt (e.g 9003_coeff.txt).
               
            
             mfcc_computation() Module
                               
                                  Inputs  - input file name i.e. the file name of the current speaker undergoing training.                                          
                                          - no of speech frames
                                          - fullpath of the input file name.
                         

                                  Outputs - fullpath of the filename where the mfcc coefficients are saved.

 Authors: 
 Creation Date : 
 Copyright:       
            
***************************************************************************
 Log of Changes
   
            Last Updated: 
            Update Notes: 

********************************************************************************/
