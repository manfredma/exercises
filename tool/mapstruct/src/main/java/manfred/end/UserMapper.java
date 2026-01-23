package manfred.end;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.text.SimpleDateFormat;
import java.util.Date;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mappings({
            @Mapping(source = "id", target = "userId"),
            @Mapping(source = "username", target = "name"),
            @Mapping(source = "email", target = "emailAddress"),
            @Mapping(source = "createTime", target = "createdAt", dateFormat = "yyyy-MM-dd HH:mm:ss")})
    UserDTO userEntityToDto(UserEntity entity);

    AddressDTO addressEntityToDto(UserAddressEntity address);

    // 自定义方法示例
    default String dateToString(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }
}