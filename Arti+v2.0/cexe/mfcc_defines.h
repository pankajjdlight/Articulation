#ifndef _MFCC_DEFINES_H_
#define _MFCC_DEFINES_H_

#define DELTA_WINDOW    3
#define DELTA_DELTA_WINDOW   3
#define PI 3.1415926536
#define FRAMESIZE 320
#define FRAMESHIFT 160
#define DFT_POINT 512                   //////////////256 point DFT.///////////// 
#define DIFF 352
#define NUM_OF_FILTER  22
#define NUM_OF_COEFFICIENTS 39
#define NUM_OF_GAUSSIANS 1024                           /*1024=POW(2,NODES)*/
#define NUM_OF_MIX   1024
#define TOTAL_MODEL 39936                               /*13312=NUM_OF_GAUSSIANS*NUM_OF_COEFFICIENTS*/
#define EPS 0.01
#define THRESHOLD 0.01
#define NODES 10					/* Total no of nodes******/
#define NUM_OF_SPEAKERS 30
#define FILE_NAME_LENGTH 30
#define TWENTY 20
#define SEVEN 7
#define FOUR 4
#define FIVE 5
#define ONE 1
#define ZERO 0
#define COHORT 5
//#define GMM_SIZE 8	/////CHANGE VALUE OF NODES IF MAKING CHANGE IN GMM_SIZE//////
#define SPEAKERIDSTART 9000
#define FIVESPEAKERS 5
#define VALID "V"		// Valid
#define INVALID "I"		// Invalid
#define CHANNELFLAG 1           // If CHANNELFLAG is 1 Cepstral Mean Substraction will be done.
#define SIZE_FULLPATH 900

#define DATABASE "/home/ast06/textfiles/database.txt"                      // Consists the list of all the trained speakers
#define SPEAKERCOUNT "/home/ast06/textfiles/Speaker_Count.txt"            // Consists the total number of trained speakers
#define COHORTSET "/home/ast06/textfiles/Cohort_set.txt"
#define GMMTABLE "/home/ast06/text_independent/textfiles/GMM_Table.txt"
//#define COHORTDIST "/home/ast06/textfiles/Cohort_distance.txt"
#define RESULT "/home/ast06/text_independent/textfiles/result.txt"
#define CLAIMEDID "/home/ast06/textfiles/claimed_id.txt"                 // Consists the ID of the current speaker who is undergoing testing
#define PERSONID "/home/ast06/textfiles/person_id.txt"                   // Consists the ID of the current speaker who is undergoing training
#define CLAIMEDID2 "/home/ast06/textfiles/claimed_id_ubm.txt"
#define COEFFS "/home/ast06/text_independent/textfiles/Coefficients.txt"
#define TOTAL_FRAMES "/home/ast06/text_independent/textfiles/total_no_frames1.txt"
#define NO_OF_FSIZE "/home/ast06/text_independent/textfiles/nof_fsize1.txt"
#define LOG_SCORE "/home/ast06/text_independent/textfiles/log_score.txt" 
#define SCORE_TI "/home/ast06/text_independent/textfiles/scores.txt" 
//#define TESTWAV "/home/ast06/recorded_files/test.wav"
//#define TESTMODEL "/home/ast06/textfiles/test_table.txt"
#define SPEECH_NONSPEECH "/home/ast06/text_independent/textfiles/speech_nonspeech.txt"   // Consists boolean array of size equal to total no. of frames, for a speeched frame value is 1 and for nonspeeched frame value is 0.
#define NO_SPEECH_FRAMES "/home/ast06/text_independent/textfiles/no_speech_frames.txt"
#define STARTING_POINT "/home/ast06/text_independent/textfiles/starting_point.txt"
#define END_POINT "/home/ast06/text_independent/textfiles/end_point.txt"
#define FP_VAR "/home/ast06/text_independent/textfiles/avr_enr.txt"
#define VAR_SCORE "/home/ast06/text_independent/textfiles/var_score.txt"
#define COUNT "/home/ast06/textfiles/count.txt"
#define ID_EXTENSION "_AM03MENR"                                       // Common extension to all the speaker ID's. AM03MENR - session(A) OnlineMobile(M03)  (M)ultienvironment (EN)glish (R)eading
#define FILE_EXTENSION ".wav"                                          // File name extension for wave file
#define AVR_ENR "/home/ast06/text_independent/textfiles/avr_enr.txt"
#define UBM_MEAN "/home/ast06/ubmlist_htkFiles/UBM_1024_SV/ubm_mean_sv"                   // Consists the means generated from UBM Modeling
#define UBM_WEIGHT "/home/ast06/ubmlist_htkFiles/UBM_1024_SV/ubm_weights_sv"               // Consists the weights generated from UBM Modeling 
#define UBM_VARIANCE "/home/ast06/ubmlist_htkFiles/UBM_1024_SV/ubm_variance_sv"           // Consists the variances generated from UBM Modeling

#define MEAN_LIVE    "/home/ast06/text_independent/live_nonlive/mean_live.txt"            // Consists the means generated from UBM Modeling
#define MEAN_NONLIVE "/home/ast06/text_independent/live_nonlive/mean_nonlive.txt"          // Consists the means generated from UBM Modeling
#define WEIGHT_LIVE  "/home/ast06/text_independent/live_nonlive/weights_live.txt"         // Consists the weights generated from UBM Modeling 
#define WEIGHT_NONLIVE "/home/ast06/text_independent/live_nonlive/weights_nonlive.txt"    // Consists the weights generated from UBM Modeling 
#define VARIANCE_LIVE "/home/ast06/text_independent/live_nonlive/var_live.txt"           // Consists the variances generated from UBM Modeling
#define VARIANCE_NONLIVE "/home/ast06/text_independent/live_nonlive/var_nonlive.txt"     // Consists the variances generated from UBM Modeling
#define LIVE  "/home/ast06/text_independent/textfiles/live.txt"
#define PATH_TEXT "/home/ast06/text_independent/textfiles/"           // Path to the directory which contains all the text files
#define PATH_TRAIN "/home/ast06/text_independent/recorded_files/train/"   // Path to the directory which contains all the recorded training files
#define PATH_TEST "/home/ast06/text_independent/recorded_files/test/"  // Path to the directory which contains all the recorded testing files
#define PATH_TEXT_MULTI "/home/ast06/textfiles/" //Path to the directory which contains all the multilevel text files.
#define PATH_COUNT "/home/ast06/textfiles/count.txt"    //Path to the directory which contains count.txt file.
#define ZERO_ORDER "/home/ast06/text_independent/textfiles/N"
#define FIRST_ORDER "/home/ast06/text_independent/textfiles/F"
#define PATH_COUNT "/home/ast06/textfiles/count.txt"
#define PATH_IVECT_TRAIN "/home/ast06/text_independent/textfiles/stats/"
#define PATH_IVECT_TEST "/home/ast06/text_independent/textfiles/test_stats/"

#endif
