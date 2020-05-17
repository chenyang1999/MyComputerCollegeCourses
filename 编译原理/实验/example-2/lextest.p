VAR I, J, K: INTEGER;
BEGIN
  I := 1;
  J := 1;
  K := 0;
  PROCEDURE TEST (I,J,K);
  BEGIN
  WHILE k<=100 DO
    BEGIN
      IF j <20 THEN
        BEGIN
          J := I;
          K := K+1
        END
      ELSE 
        BEGIN
          J := K;
          K := K-2
        END
    END
  END
END.