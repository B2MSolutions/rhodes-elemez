/* elemez.i */

%module Elemez
%{
#include "ruby/ext/rho/rhoruby.h"

extern void elemez_native_raiseDisruption(const char* sender, const char* source, int userInitiated);
#define native_raiseDisruption elemez_native_raiseDisruption 

%}

extern void native_raiseDisruption(const char* sender, const char* source, int userInitiated);