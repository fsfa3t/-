#include "stdio.h"
#include "math.h"
#include "unistd.h"
#define A 1
int main()
{
    while (1)
    {

        int height = 0;                         // 总高度(30) 3<=height<=32
        printf("输入星星的高度【3~32之间】："); // 提示用户输入星星的高度
        scanf("%d", &height);                   // 输入星星的高度
        int x = 18;                             // 控制上半空白区域(19)18<=x
        int y = 2.0 / 3.0 * height - 20;        // 控制上半星星(1)17<=y
        int a1 = x * 2 - 35;                    // 控制下半空白区域(3)
        int c1 = 2 * y + 35;                    // 控制下半星星(37)
        if (height < 3 || height > 32)          // 判断输入的高度是否在范围内
        {
            printf("检测到您超过范围,可能会出图案不正确是否继续？\n【回车并继续……】");
            while (getchar() != '\n')
                ; // 清空输入缓冲区，直到遇到换行符为止

            char INPUT = getchar(); // 等待用户输入
            if (INPUT != '\n')
            {
                printf("感谢你的使用，程序退出！\n");
                break;
            }
            else
            {
                printf("继续……\n");
            }
        }
        for (int a = A; a <= height / 3; a++)
        {
            for (int b = A; b <= x; b++)
            {
                printf(" ");
            }

            for (int c = A; c <= y; c++)
            {
                printf("*");
            }
            for (int d = A; d <= y; d++)
            {
                printf("*");
            }

            for (int c = A; c <= x; c++)
            {
                printf(" ");
            }

            for (int b = A; b <= x; b++)
            {
                printf(" ");
            }

            for (int d = A; d <= y; d++)
            {
                printf("*");
            }
            for (int e = A; e <= y; e++)
            {
                printf("*");
            }
            x -= 2;
            y += 2;
            printf("\n");
        }

        for (int i = A; i <= height / 3 * 2; i++)
        {
            for (int j = A; j <= a1; j++)
            {
                printf(" ");
            }
            a1 += 2;
            for (int k = A; k <= c1; k++)
            {
                printf("*");
            }
            for (int l = A; l <= c1; l++)
            {
                printf("*");
            }
            c1 -= 2;
            printf("\n");
        }
    }
}
