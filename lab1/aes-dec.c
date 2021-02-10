#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/evp.h>

int main(int argc, const char* argv[])
{
  if (argc <= 3) {
    fprintf(stderr, "Usage: %s <key> <iv> <data>\n", argv[0]);
    exit(EXIT_FAILURE);
  }
  
  const char  *data;
  int key_length, iv_length, data_length;
  unsigned int key;
  char keyarray[16];
  unsigned int iv;
  char ivarray[16];
  int j =0;
 
  while(sscanf(argv[1],"%02X",&key)==1){
        
        argv[1]+=2;
        keyarray[j]=key;
        j++;
  }
  j=0;
  while(sscanf(argv[2],"%02X",&iv)==1){
        
        argv[2]+=2;
        ivarray[j]=iv;
        j++;
  }
    
  data = argv[3];
  data_length = strlen(data);

  const EVP_CIPHER *cipher;
  //int cipher_key_length, cipher_iv_length;
  cipher = EVP_aes_128_cbc();
 // cipher_key_length = EVP_CIPHER_key_length(cipher);
 // cipher_iv_length = EVP_CIPHER_iv_length(cipher);

  const char *p;
  char *datax;
  int i, datax_length;

  datax = (char *)malloc(data_length);
  for (p = data, i = 0; *p != '\0'; p += 2, i++) {
    char buf[3];
    buf[0] = *p;
    buf[1] = *(p+1);
    buf[2] = '\0';
    datax[i] = strtol(buf, NULL, 16);
  }
  datax_length = i;
  EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();

  EVP_CIPHER_CTX_init(ctx);
  EVP_DecryptInit_ex(ctx, cipher, NULL, keyarray, ivarray);

  int plain_length, final_length;
  unsigned char *plaintext;

  plain_length = datax_length;
  plaintext = (unsigned char *)malloc(plain_length + 1);

  EVP_DecryptUpdate(ctx, plaintext, &plain_length, (unsigned char *)datax, datax_length);
  EVP_DecryptFinal_ex(ctx, plaintext + plain_length, &final_length);

  plaintext[plain_length + final_length] = '\0';
  printf("%s\n", plaintext);

  free(plaintext);
  free(datax);

  EVP_CIPHER_CTX_cleanup(ctx);

  return 0;
}

