#include<stdio.h>
#include<stdlib.h>
#include "mfcc_defines.h"
#include "mfcc_globals.h"
/*******************************   Dependency ::::  *******************************************************************
                                                   (i)   vad.c
                                                   (ii)  mfcc_computation.c
                                                   (iii) stat_comp.c

***********************************************************************************************************************/

short    *vad_enrthr(char *, int *, int *, int *, char *, 
                  char *, char *, char *, char *);
char     *mfcc_computation(char *, int, char *, int, char *);
float    **ComputeDeltaCoefficients(float **inpFeatures, int numFeatures, int dimFeats, int K);
float    **ComputeMFCC(char waveName[], int numSpeechFrames, short *speechNonSpeech, int nof_fsize, int totalFrames,char *a);
void     stat_comp(char id[], short *speech_nonspeech, int no_of_speech_frames, int total_no_of_frames, char features_filename[], char zeroOrderStats_fileName[], char firstOrderStats_fileName[]);
void     StatisticsCompV(char *, float **, int, int, char *, char *);
float    **ConcatenateFeaturesWithOffset(float **feature1, int rows1, int cols1, float **feature2, int rows2, int col2, int offset);
float    **ConcatenateFeatures(float **feature1, int rows1, int cols1, float **feature2, int rows2, int cols2);
void     cms(float **mfccCoeffs, int totalFrames,int numCoeffs);
void     ZeroMeanUnitVariance(float **features, int totalFrames,int numCoeffs);
//void map_training(char *, short *, int, int, char *);



int main(int argc, char *argv[]){  

   if (argc != 12){
      printf("Usage::: ./ComputeMFCCDeltaDelta   full_path_input(waveFile)  id_(phone Number)  strt_frame_fileName(op)  end_frame_fileName(op)  voiced_unvoiced_fileName(op)  \
                          speech_frameNo_fileName(op)  Average_Energy_FileName(op)   Zeroth_Order_Stats_FileName(op)   First_Order_Stats_FileName(op) MFCC_Delta_Delta_Coeff_File(op) \n");
       exit(0);
                 } 
 //  printf(" I/P file \t %s  \n", argv[1]);
 //  printf(" I/P file \t %s  \n", argv[11]);
  
  // char *articulate="";
  // *articulate=argv[11];
   //printf(" error strinng is------>%s\n",argv[11]);
   char *input_filename, *fullpath_input, *id;
   short *speech_nonspeech_frames;
   int i;
   char *mfcc_filepath;
   int no_of_speech_frames;
   int total_no_of_frames, nof_fsize=0;
   int mfccDim = 39,xxxx=0;
   //
   //char argv[1] = "C:\\Users\\user\\Desktop\\kaka.wav";
   //char argv[1] = "C:\\Users\\user\\Desktop\\kaka.wav";
   
  

   // printf("File %s", argv[1]);   
   /*****************calling the voice activity detection_energy threshold function to do the preprocessing and return the speech frames**********************************/ 
   //speech_nonspeech_frames = vad_enrthr(argv[1], &total_no_of_frames, &no_of_speech_frames, &nof_fsize);
   speech_nonspeech_frames = vad_enrthr(argv[1], &total_no_of_frames, &no_of_speech_frames, &nof_fsize, argv[3], argv[4], argv[5], argv[6], argv[7]);
   
   //printf("Voice Activity detection module execution is done. \n Input is  Fullpath of input: %s \n Output of module are Total No of frames : %d No of speech frames : %d  and speech frames array\n.", argv[1], total_no_of_frames,   no_of_speech_frames); 
    //printf("FILE PATH IS \t %s \n ",  argv[1]);

  

   /*****************calling the feature extraction function  to calculate the mfcc coefficients and return its fullpath**********************************/ 


    FILE *opMfccDD,*MFCC;
    opMfccDD = fopen(argv[10], "w");
    MFCC = fopen("mfcc_test.txt", "w");
  //  printf("argv 10 is \t %s\n ", argv[10]);
   //// Count the Number of Lines ..... 
    float  **mfccCoeffs, **deltaCoeffs, **deltaDeltaCoeffs;
    mfccCoeffs = (float **) calloc(total_no_of_frames, sizeof(float *));  // Define the Mfcc Coefficients to have <<< NUMSPEECHFRAMES >>> 
    int dimIter = 0, iter=0;
     // Computing the Static MFCC Coefficients  ...
    
    //printf("\n\n\n\n\n\nCCCHHHEEECCCCCKKKKKKK %d\n\n\n\n\n\n\n\n\n",nof_fsize);
    mfccCoeffs = ComputeMFCC(argv[1], no_of_speech_frames, speech_nonspeech_frames, nof_fsize, total_no_of_frames,argv[11]);
    //printf("\n\n\n\n\n\nCCCHHHEEECCCCCKKKKKKK %d\n\n\n\n\n\n\n\n\n",nof_fsize);
  //  printf("\n MFCC Computation is Done \n");

    //  Calling the Delta Coefficient Now ..... 
 // deltaCoeffs = ComputeDeltaCoefficients(mfccCoeffs, total_no_of_frames, NUM_OF_COEFFICIENTS, DELTA_WINDOW);  // the return value if of the 
//  same dimension as that of the MFCC COeffs
   // printf("\n MFCC + Delta is done \n");
    // Computing the Delta Delta Coefficient Now .... 
 // deltaDeltaCoeffs = ComputeDeltaCoefficients(deltaCoeffs, total_no_of_frames, NUM_OF_COEFFICIENTS, DELTA_WINDOW); // same size as that of the delta Coefficient   i.e. same size of the  MFCC's ..... 
    //printf("\n MFCC + Delta + Delta is done \n");
     
    // Concatenate MFCC with Delta Features  to obtain MFCC-Delta-Delta Features ....
  //  float           **mfccDelta, **mfccDeltaDelta; 
 // mfccDelta       = ConcatenateFeatures(mfccCoeffs, total_no_of_frames, NUM_OF_COEFFICIENTS, deltaCoeffs, total_no_of_frames, NUM_OF_COEFFICIENTS);
 // mfccDeltaDelta  = ConcatenateFeatures(mfccDelta, total_no_of_frames, 2 * NUM_OF_COEFFICIENTS, deltaDeltaCoeffs, total_no_of_frames, NUM_OF_COEFFICIENTS);
   
//    int coeffDim  = 3 * NUM_OF_COEFFICIENTS; 
 //   float **mfccDDSpeech;
//  mfccDDSpeech   = (float **) calloc (total_no_of_frames, sizeof(float *));                               
 //  printf("Writing the New Modified Code Mfcc Delta Delta Coefficient to a file \n");
  //  int sphFrme = -1;
/*  for(iter = 0; iter < total_no_of_frames; iter++){
      
      // if(speech_nonspeech_frames[iter]) {
            mfccDDSpeech[++sphFrme]  = (float *) calloc (coeffDim, sizeof(float ));
            for(dimIter = 0; dimIter < coeffDim; dimIter++)
                         mfccDDSpeech[sphFrme][dimIter] = mfccDeltaDelta[iter][dimIter];
            
                                 //          }
                                                                  
                                                }  // End of <<iter>> variable loop 
            //fclose(opMfccDD);
                                         
*/

    //cms(mfccDDSpeech, no_of_speech_frames, coeffDim);       


  for(int i =0;i<total_no_of_frames;i++){
        for(dimIter = 0; dimIter < mfccDim; dimIter++){
        //printf("This is the one %f \n",mfccCoeffs[i][dimIter]);
        //fprintf(opMfccDD, "%f ", mfccCoeffs[i][dimIter]);
        //printf("\n\n\n\n\n\nCCCHHHEEECCCCCKKKKKKK %d\n\n\n\n\n\n\n\n\n",i);
    }
    }
   
     // Writing the MFCC+D+DD coefficeint to the file ....
    
 //printf("\n\n\n\n\n\nCCCHHHEEECCCCCKKKKKKK %d\n\n\n\n\n\n\n\n\n",total_no_of_frames);
    
    
    
    
    for(iter = 19; iter <67; iter++){
            for(dimIter = 0; dimIter < mfccDim; dimIter++)
                {
                          //printf("CHECK... %d\n",total_no_of_frames);
                          printf(" VALUE is %f\t", mfccCoeffs[iter][dimIter]);
                          fprintf(MFCC, "%f ", mfccCoeffs[iter][dimIter]);
                          fprintf(opMfccDD, "%f ", mfccCoeffs[iter][dimIter]);
                }
            
            
            if(speech_nonspeech_frames[iter]){
            fprintf(opMfccDD, "\n");
          printf("\n");  
            }
        // printf("\n\n\n\n\n\nCCCHHHEEECCCCCKKKKKKK %d\n\n\n\n\n\n\n\n\n",iter);
    }  // End of <<iter>> variable loop 
    fclose(opMfccDD);
    fclose(MFCC);
                         
   
   
   // StatisticsCompV(argv[2], mfccDDSpeech, no_of_speech_frames, coeffDim, argv[8], argv[9]);       
 //  void    stat_comp(char id[], short *speech_nonspeech, int no_of_speech_frames, int total_no_of_frames, char features_filename[], char zeroOrderStats_fileName[], char firstOrderStats_fileName[]); 

    return 0;
   
             }  


