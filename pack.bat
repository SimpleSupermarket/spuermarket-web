rem
cls

@echo off
color a

::批处理命令开始-----------------------------------
setlocal enabledelayedexpansion



set APP_NAME=oa-base
set TAR_FILE_NAME=%APP_NAME%.jar
set TMP_PATH=temp\%APP_NAME%\


:start
echo 打包开始

del %TAR_FILE_NAME%
rd /s/q temp

mkdir temp
xcopy /y config %TMP_PATH%\config\

xcopy /y target\oa-base-1.0.*-SNAPSHOT.jar %TMP_PATH%
xcopy /y sh %TMP_PATH%

jar -cvfM %TAR_FILE_NAME% -C temp .


rd /s/q temp
echo 打包结束
echo %TAR_FILE_NAME%.zip

pause::[提示按任意键结束...]
goto end

:end
:exit
