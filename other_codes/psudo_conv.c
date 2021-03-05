#define IMGX 1
#define IMGY 1
#define n 1

int k[IMGY + 2][IMGX + 2];

void conv() {
int img[IMGY + 2][IMGX + 2];
int filt[IMGY][IMGX];
int n2 = n / 2;

for (int x = 1; x <= IMGX; x++) {
  for (int y = 1; y <= IMGY; y++) {
    int newV = 0;

    for (int i = -n2; i <= n2; i++)
      for (int j = -n2; j <= n2; j++)
        newV += img[y - j][x - i] * k[n2 + j][n2 + i];

    filt[y - 1][x - 1] = newV;
  }
}
}