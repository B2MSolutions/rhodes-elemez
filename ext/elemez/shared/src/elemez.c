#ifdef __cplusplus
extern "C" {
#endif

// declare Ruby registration method generated by SWIG in elemez_wrap.c
extern void Init_Elemez(void);

// this method executed once on start of program
void Init_Elemez_extension(void) {
	// execute initialization of Ruby registration (generated by SWIG)
	Init_Elemez();
}

#ifdef __cplusplus
} //extern "C"
#endif