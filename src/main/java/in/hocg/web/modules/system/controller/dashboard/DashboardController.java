package in.hocg.web.modules.system.controller.dashboard;

import in.hocg.web.modules.base.BaseController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hocgin on 2017/11/14.
 * email: hocgin@gmail.com
 */
@Controller
@RequestMapping("/admin/dashboard")
@PreAuthorize("hasPermission(null, 'dashboard')")
public class DashboardController extends BaseController {
    public final String BASE_TEMPLATES_PATH = "/admin/dashboard/%s";
    
    @GetMapping({"/index.html", "/"})
    public String index() {
        return String.format(BASE_TEMPLATES_PATH, "index");
    }
}
