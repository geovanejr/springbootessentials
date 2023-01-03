package br.com.geovanejunior.mapper.exception;

import br.com.geovanejunior.error.ResourceNotFoundDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ExceptionMapper {

    ResourceNotFoundDetails rfnDetailsMapper(long timestamp, int status, String title, String detail, String developerMessage);
}
