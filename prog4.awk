BEGIN{
 pktrc=0;
 tp=0;
 }
 {
  if (($1=="r")&&($3=="_3_")&&($4=="AGT")&&($7=="tcp")&&($8>1000))
  {
    pktrc++;
   }
  }
  END{
  tp =((pktrc*1000*8)/(95.0*1000000))
  printf("%f\n",tp)
  }
   
