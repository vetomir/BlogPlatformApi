package pl.gregorymartin.newsportal.tag;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TagService {
    private static final int PAGE_SIZE = 28;

    private final TagRepository tagRepository;

    TagService(final TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    List<Tag> getAllTags(){
        return tagRepository.findAll();
    }

    public List<Tag> getTags(int page, Sort.Direction sort, String sortBy) {

        return tagRepository.findAll(
                PageRequest.of(page, PAGE_SIZE,
                        Sort.by(sort, sortBy)
                )
        ).getContent();
    }

    Tag getSingleTag(long tagId){
        Optional<Tag> tag = tagRepository.findById(tagId);
        if(tag.isEmpty()){
            throw new IllegalArgumentException("Tag (ID:" + tagId + ") is not exists");
        }
        return tag.get();
    }

    Tag getSingleTag(String tagName){
        Optional<Tag> tag = tagRepository.findByName(tagName);
        if(tag.isEmpty()){
            throw new IllegalArgumentException("Tag " + tagName + " is not exists");
        }
        return tag.get();
    }

    public Tag addTag(Tag source){
        Optional<Tag> tag = tagRepository.getByName(source.getName());
        if (tag.isEmpty()) {
            return tagRepository.save(source);
        }
        return tag.get();
    }

    public Set<Tag> saveTags(Set<Tag> tags){
        Set<Tag> tagSet = tags.stream().map(x -> {
            Optional<Tag> tag = tagRepository.getByName(x.getName());

            if (tag.isEmpty()) {
                return tagRepository.save(x);
            } else {
                return tag.get();
            }
        }).collect(Collectors.toSet());

        return tagSet;
    }

    @Transactional
    Tag editTagName(Tag source){
        Tag tag = getSingleTag(source.getId());
        tag.setName(source.getName());
        return tag;
    }

    boolean deleteTag(long id){
        Tag tag = getSingleTag(id);
        tagRepository.delete(tag);
        return true;
    }
}
