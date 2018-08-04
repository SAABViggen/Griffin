call runcrud
if "%ERRORLEVEL%" == "0" goto showtasks
echo.
echo Something went wrong, sorry...
goto fail

:showtasks
echo.
"C:\Program Files\Internet Explorer\iexplore.exe" http://localhost:8080/crud/v1/task/getTasks
if "%ERRORLEVEL%" == "0"
echo It works! :D
goto end

:fail
echo.
echo It doesn't work :(

:end
echo.
echo Finished.