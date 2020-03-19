DATA toads;
    INFILE 'ToadJump.dat';
    INPUT ToadName $ Weight Jump1 Jump2 Jump3;
RUN;
PROC PRINT data=toads;
    TITLE ' SAS Data Set Toads';
RUN;