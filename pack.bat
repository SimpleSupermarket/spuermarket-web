rem
cls

@echo off
color a

::���������ʼ-----------------------------------
setlocal enabledelayedexpansion



set APP_NAME=oa-base
set TAR_FILE_NAME=%APP_NAME%.jar
set TMP_PATH=temp\%APP_NAME%\


:start
echo �����ʼ

del %TAR_FILE_NAME%
rd /s/q temp

mkdir temp
xcopy /y config %TMP_PATH%\config\

xcopy /y target\oa-base-1.0.*-SNAPSHOT.jar %TMP_PATH%
xcopy /y sh %TMP_PATH%

jar -cvfM %TAR_FILE_NAME% -C temp .


rd /s/q temp
echo �������
echo %TAR_FILE_NAME%.zip

pause::[��ʾ�����������...]
goto end

:end
:exit
