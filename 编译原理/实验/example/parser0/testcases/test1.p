PROGRAM test;
VAR i, j, k: INTEGER;
    f0: REAL;
BEGIN
  i := 1;
  j := 1;
  k := 0;
  f0 := 3.2;
  WHILE k<=100 DO
    BEGIN
      IF j <20 THEN
        BEGIN
          j := i;
          k := k+1;
          f0 := f0*0.2
        END
      ELSE 
        BEGIN
          j := k;
          k := k-2;
          f0 := f0/.2
        END
    END
END.