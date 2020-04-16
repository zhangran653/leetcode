### 一个动态规划解决6个问题
1. 定义
    - dp[i][k][0]: i天，k次交易，0表示持有现金，1表示持有股票。
2. 状态转移
    - dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
    第i天不持有：前一天不持有，今天保持，或者前一天持有，今天卖出
    
    - dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
    第i天持有：前一天持有，今天保持，或者前一天不持有，今天买入
3. base case
    
4. k = 1,仅能完成一次交易
    - 状态转移方程
        - dp[i][1][0] = max(dp[i-1][1][0], dp[i-1][1][1] + prices[i])
        - dp[i][1][1] = max(dp[i-1][1][1], dp[i-1][0][0] - prices[i])
    
    - 状态转移方程与k无关，简化为：
        - dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
        - dp[i][1] = max(dp[i-1][1], -prices[i])
        
    - base case:
        - dp[0][0] 表示0次交易，没有持有股票。因此利润为0
        - dp[0][1] 0天买入，利润为-prices[0]
        
5. k = 无限，无交易次数限制
    - 状态转移方程
        - dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
        - dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
        - k为+无穷，k与k-1相同，简化
            - dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
            - dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i])
        
    - base case:
        - dp[0][0] 表示0次交易，没有持有股票。因此利润为0
        - dp[0][1] 0天买入，利润为-prices[0]
        
6. k = +infinity + cool down
    - 状态转移方程
        - dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
        - dp[i][1] = max(dp[i-1][1], dp[i-2][0] - prices[i]), 买入需要等待一天，所以是i-2
    - base case:
        - dp[0][0] = 0 表示0次交易，没有持有股票。因此利润为0
        - dp[0][1] = -prices[0] 0天买入，利润为-prices[0]
        - dp[-1][0] = 0
        
7. k = +infinity + fee
    - 状态转移方程
        - dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
        - dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i] - fee), 买入需要fee
    - base case:
        - dp[0][0] = 0 表示0次交易，没有持有股票。因此利润为0
        - dp[0][1] = -prices[0] - fee 0天买入，利润为-prices[0] - fee
        
8. k = 2，最多2笔交易
    - 状态转移方程
        - dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
        - dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
    - 循环k的状态
        for(int k=1;k<=2;k++)
        
9. k次交易：
    - 同k=2，但是k>n/2时特殊处理 
        