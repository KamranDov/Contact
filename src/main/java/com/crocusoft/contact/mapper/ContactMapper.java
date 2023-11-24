package com.crocusoft.contact.mapper;

import com.crocusoft.contact.domain.Contact;
import com.crocusoft.contact.dto.ContactDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ContactMapper {

    public Contact dtoToEntity(ContactDto contactDto);
}
