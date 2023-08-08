pushd todo-frontend
call npm run build
popd

@echo off
set list=TP2 TP3 TP4-bis TP5 TP5-bis TP6 TP7 TP7.1 TP8 TP9 TP10 TP11 TP12 TP13 TP14 TP15 TP16 TP17 TP18
(for %%a in (%list%) do (
    rmdir /s /q todo-project-%%a\src\main\resources\static
    xcopy /s /e /i todo-frontend\dist\todo-frontend todo-project-%%a\src\main\resources\static
))