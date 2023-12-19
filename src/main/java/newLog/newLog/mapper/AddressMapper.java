package newLog.newLog.mapper;

import newLog.newLog.dto.AddressDto;
import newLog.newLog.model.Address;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    List<AddressDto> addressesToDtos(List<Address> addresses);

   // @Mapping(target = "id", source = "addressId")
    AddressDto addressToDto(Address addresses);


   // @InheritInverseConfiguration
    Address dtoToAddress(AddressDto addressDto);

    List<Address> dtosToAddresses(List<AddressDto> addresses);

    AddressMapper MAPPER = Mappers.getMapper(AddressMapper.class);


}
