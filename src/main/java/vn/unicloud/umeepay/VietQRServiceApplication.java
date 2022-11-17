package vn.unicloud.umeepay;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import vn.unicloud.umeepay.entity.*;
import vn.unicloud.umeepay.enums.*;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.service.RoleService;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class VietQRServiceApplication implements CommandLineRunner {

    private static String fullPermissionKey = "CMS_FULL_PERMISSIONS";

    private final RoleService roleService;

    public static void main(String[] args) {
        SpringApplication.run(VietQRServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        importPermissionGroup();
        createFullPermissionRoleGroup();
    }

    public void importPermissionGroup() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        log.info(" ===> Start import permission groups");
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        TypeReference<List<PermissionGroup>> typeReference = new TypeReference<List<PermissionGroup>>() {};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/permissions.json");
        List<PermissionGroup> permissionGroups = mapper.readValue(inputStream, typeReference);

        for (PermissionGroup permissionGroup : permissionGroups) {
            if (roleService.savePermission(permissionGroup) == null) {
                throw new InternalException(ResponseCode.FAILED);
            }
            log.info("Imported permission group {}", permissionGroup.getName());
        }

        log.info(" =====> Stop import permission groups");
    }

    public void createFullPermissionRoleGroup() {
        log.info(" ===> Start import full permissions role group");
        if (roleService.getRoleByCode(fullPermissionKey, RoleType.ADMIN) == null) {
            RoleGroup roleGroup = new RoleGroup()
                    .setName(fullPermissionKey)
                    .setCode(fullPermissionKey)
                    .setScope(RoleType.ADMIN)
                    .setDescription("Full permission role group")
                    .setStatus(RoleStatus.ACTIVE);

            RoleGroup group = roleService.saveRole(roleGroup);
            Assert.notNull(group);
        }
        log.info(" ===> Stop import full permissions role group");
    }
}
