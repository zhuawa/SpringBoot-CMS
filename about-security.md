
## `/admin/**`
- CSRF 
- 需要认证

### 允许匿名访问
- `/admin/login.html` 登陆
- 静态资源
  - `/favicon.ico`
  - `/**/*.css`
  - `/**/*.js`
  - `/**/*.woff`
  - `/**/*.ttf`
  - `/**/*.jp`
  - `/**/*.woff2`

-----
## `/api/**`
- JWT
### 允许匿名访问
- `/api/auth/**` 获得授权

-----
## `/public/**`
- 公共入口



## 笔记
### AuthenticationManager
> 管理认证

### AuthenticationProvider
> 认证过程

### UserDetailsService
> 获取用户信息

### UserDetails
> 暴露的用户信息

### SessionRegistry


### `in.hocg.web.modules.system.domain.User`
> 用户
> username 与 mail 为全站唯一
- 用户(User)
    - 管理员 (Manager)
    - 会员 (Member)

1. 是否删除 nickname

