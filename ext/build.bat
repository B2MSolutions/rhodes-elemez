
if "%RHO_PLATFORM%" == "android" (

cd elemez\platform\android
rake --trace

)

if "%RHO_PLATFORM%" == "iphone" (

cd elemez\platform\phone
rake --trace

)

if "%RHO_PLATFORM%" == "wm" (

cd elemez\platform\wm
rake --trace

)

if "%RHO_PLATFORM%" == "win32" (

cd elemez\platform\wm
rake --trace

)

if "%RHO_PLATFORM%" == "bb" (

cd elemez\platform\bb
rake --trace

)

