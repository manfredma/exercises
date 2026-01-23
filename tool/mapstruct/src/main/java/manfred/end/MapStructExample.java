package manfred.end;

import java.util.Date;

public class MapStructExample {
    public static void main(String[] args) {
        // 创建源对象
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setUsername("zhangsan");
        userEntity.setPassword("123456");
        userEntity.setEmail("zhangsan@example.com");
        userEntity.setCreateTime(new Date());
        
        UserAddressEntity addressEntity = new UserAddressEntity();
        addressEntity.setStreet("朝阳路");
        addressEntity.setCity("北京");
        addressEntity.setZipCode("100000");
        userEntity.setAddress(addressEntity);
        
        // 使用映射器转换对象
        UserDTO userDTO = UserMapper.INSTANCE.userEntityToDto(userEntity);
        
        // 输出结果
        System.out.println("UserDTO: " + userDTO);
    }
}