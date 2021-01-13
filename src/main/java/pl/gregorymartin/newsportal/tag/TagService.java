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

    List<Tag> showAllTags(){
        return tagRepository.findAll();
    }

    public Tag addTag(Tag source){
        Optional<Tag> tag = tagRepository.findById(source.getId());
        return tag.orElseGet(() -> tagRepository.save(source));
    }

    @Transactional
    Tag editTagName(Tag source){
        Optional<Tag> tag = tagRepository.findById(source.getId());
        if(tag.isEmpty()){
            throw new IllegalArgumentException("Tag is not exist");
        }
        tag.get().setName(source.getName());
        return tag.get();
    }

    boolean deleteTag(long id){
        if(!tagRepository.existsById(id)){
            throw new IllegalArgumentException("Tag is not exists");
        }
        tagRepository.deleteById(id);
        return true;
    }
}
