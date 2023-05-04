#include<bits/stdc++.h>
 
#define  ll      long long
#define  db      double
#define  vii     vector<ll>
#define  pll     pair<ll, ll>
#define  mpl     map<ll, ll>
#define  F       first
#define  S       second
 
const ll mod = (ll)1e9+7;
const ll inf = (ll)1e18;
const db eps = (db)1e-9;
 
using namespace std;

ll dp[205][205];
ll par[205][205];
ll cost[205], val[205];
ll n;
vii v;

ll DP(ll i, ll tar) {
    if(i > n) {
        return 0;
    }
    ll &ans = dp[i][tar];
    if(ans != -1) return ans;
    ans = DP(i+1, tar);
    par[i][tar] = tar;
    if(tar >= cost[i]) {
        ll pur = val[i]+DP(i+1, tar-cost[i]);
        if(pur > ans) ans = pur, par[i][tar] = tar-cost[i];
    }
    return ans;
}

void path(ll i, ll tar) {
    if(i > n) return;
    if(par[i][tar] != tar) {
        v.push_back(i);
        path(i+1, tar-cost[i]);
        return;
    }
    path(i+1, tar);
}

int main()
{
    freopen("input3.txt","r",stdin);
    freopen("output3.txt","w",stdout);
 
    int t=1;
    cin >> t;
 
    while(t--){
 
        ll a, b, c, d, e, i, j, k, m, x, y;
        memset(dp, -1, sizeof(dp));
        cin >> n >> a;
        for(i=1; i<=n; i++) {
            cin >> cost[i];
            for(j=0; j<205; j++) par[i][j] = j;
        }
        for(i=1; i<=n; i++) {
            cin >> val[i];
        }
        cout << DP(1, a) << "\n";
        path(1, a);
        cout << v.size() << "\n";
        for(ll ii : v) cout << ii << ' ';
        cout << "\n";
        v.clear();
        
    }
    return 0;
}0;
}