package pl.gregorymartin.newsportal.tag.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public
class TagQueryReadModel {
    private long id;
    private String name;
}
