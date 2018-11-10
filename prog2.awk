#!/usr/bin/awk -f
BEGIN{
 drop=0;
 }
{
 if($1=="d"){drop++;}
 }
 END{
   printf("%d\n",drop);
   }
