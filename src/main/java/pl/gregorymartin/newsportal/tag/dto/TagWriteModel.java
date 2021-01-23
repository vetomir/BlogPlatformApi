package pl.gregorymartin.newsportal.tag.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public
class TagWriteModel {
    private String name;
}
