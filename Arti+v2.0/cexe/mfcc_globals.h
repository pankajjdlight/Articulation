#ifndef _MFCC_GLOBALS_H_
#define _MFCC_GLOBALS_H_

struct audio
{

	int Chunk_ID;
	int Chunk_Size; 
	int Format;
	int SubChunk1_ID;
	int SubChunk1_Size;
	short Audio_Format;
	short Number_of_Channels;
	int Sample_Rate;
	int Byte_Rate;
	short Block_Align;
	short Bits_Per_Sample;
	int SubChunk2_ID;
	int Subchunk2_Size;
       

};
typedef struct audio waveStruct;	



struct speech_recognition
{

	waveStruct header;
	float sample_value[DFT_POINT];					// input data 
	float dft_output1[2][DFT_POINT];	
	float final_arg1[DFT_POINT];
	float hamming_output1[DFT_POINT];
        float eop_output2[DFT_POINT];
	float Filter_resp[NUM_OF_FILTER+1][DFT_POINT];	               // filter response array
	float warping_spectrum1[NUM_OF_FILTER];
	float log_magnitude1[NUM_OF_FILTER];
	float IDCT_Output1[NUM_OF_COEFFICIENTS];
        float A_out[NUM_OF_FILTER];
        float mahal_out[];
        
};

typedef struct speech_recognition speech_recognition_struct;

speech_recognition_struct globDataSpeechRecgStruct;

//int total_coeff;
//int Speaker_ID;
//int g_nof_fsize;   // no of frames of frame size without overlapping

#endif
