package vn.unicloud.umeepay;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.Assert;
import vn.unicloud.umeepay.entity.Action;
import vn.unicloud.umeepay.entity.Permission;
import vn.unicloud.umeepay.enums.RoleType;
import vn.unicloud.umeepay.service.RoleService;

import java.util.Arrays;

@SpringBootApplication
@RequiredArgsConstructor
public class VietQRServiceApplication implements CommandLineRunner {

    private final RoleService roleService;

    public static void main(String[] args) {
        SpringApplication.run(VietQRServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        importPermissions();
    }

    private void importPermissions() {
        Permission permission = new Permission()
                .setName("CMS_ADMIN_MANAGEMENT")
                .setScope(RoleType.ADMIN)
                .setDescription("Cms admin management")
                .setActions(Arrays.asList(
                        new Action().setName("CMS_CREATE_ADMIN"),
                        new Action().setName("CMS_UPDATE_ADMIN"),
                        new Action().setName("CMS_DELETE_ADMIN"),
                        new Action().setName("CMS_BLOCK_ADMIN"),
                        new Action().setName("CMS_UNBLOCK_ADMIN")
                ));

        Permission savedPer = roleService.savePermission(permission);
        Assert.notNull(savedPer);

    }
}
