#include <stdlib.h>
#include <stdio.h>
#include <string.h>
 
int function(char *string) {
 
  char buffer[1024];

   strcpy(buffer, string);

  printf("Thank You!\n");
 
  return 1;
}
 
int main(int argc, char *argv[]) {
  if (argc > 1) {
    function(argv[1]);
  }

  printf("Done..\n");
 
  return 1;
}
