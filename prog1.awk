#!/usr/bin/awk -f
BEGIN{
 cbrPkt=0;
 tcpPkt=0;
}
{
 if(($1=="d") && ($5=="cbr")) {cbrPkt++;}
 if(($1=="d") && ($5=="tcp")) {tcpPkt++;}
}
END{
 printf("Cbr drop: %d\n",cbrPkt);
 printf("tcp drop: %d\n",tcpPkt);
 }
