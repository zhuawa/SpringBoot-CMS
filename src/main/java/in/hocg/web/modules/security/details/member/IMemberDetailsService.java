package in.hocg.web.modules.security.details.member;

import in.hocg.web.lang.utils.RequestKit;
import in.hocg.web.modules.security.exception.IAuthenticationException;
import in.hocg.web.modules.system.domain.Member;
import in.hocg.web.modules.system.domain.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by hocgin on 2017/11/25.
 * email: hocgin@gmail.com
 */
@Service("IMemberDetailsService")
public class IMemberDetailsService implements UserDetailsService {
    private MemberRepository memberRepository;
    private HttpServletRequest request;
    
    @Autowired
    public IMemberDetailsService(MemberRepository memberRepository,
                                 HttpServletRequest request) {
        this.memberRepository = memberRepository;
        this.request = request;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws AuthenticationException {
        Member member = memberRepository.findByEmail(username);
        if (ObjectUtils.isEmpty(member)) {
            // 未找到用户
            throw new IAuthenticationException("账号或密码错误");
        }
        if (!member.getAvailable()) {
            throw new IAuthenticationException("账号被锁定");
        }
        if (!member.getIsVerifyEmail()) {
            throw new IAuthenticationException("邮箱未验证");
        }
        member.setLogInIP(RequestKit.getClientIP(request));
        member.setUserAgent(RequestKit.getUserAgent(request));
        member.setLogInAt(new Date());
        memberRepository.save(member);
        return IMember.toIMember(member);
    }
}
