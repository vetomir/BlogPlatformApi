package pl.gregorymartin.newsportal.tag;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {
    private final TagRepository tagRepository;

    TagService(final TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    List<Tag> getAllTags(){
        return tagRepository.findAll();
    }

    Tag getSingleTag(long tagId){
        Optional<Tag> tag = tagRepository.findById(tagId);
        if(tag.isEmpty()){
            throw new IllegalArgumentException("Tag is not exists");
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
        Optional<Tag> tag = tagRepository.findByName(source.getName());
        if (tag.isEmpty()) {
            return tagRepository.save(source);
        }
        return tag.get();

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
