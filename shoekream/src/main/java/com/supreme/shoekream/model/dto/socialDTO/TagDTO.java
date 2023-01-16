package com.supreme.shoekream.model.dto.socialDTO;

import com.supreme.shoekream.model.dto.ProductDTO;
import com.supreme.shoekream.model.entity.Tag;

import java.util.ArrayList;
import java.util.List;

public record TagDTO(
        Long boardIdx,
        ProductDTO productDTO
) {
    public static TagDTO of(Long boardIdx, ProductDTO productDTO){
        return  new TagDTO(boardIdx, productDTO);
    }

    public static TagDTO fromEntity(Tag tag){
        return new TagDTO(
                tag.getBoard().getIdx(),
                ProductDTO.fromEntity(tag.getProduct())
        );
    }

    public static List<TagDTO> fromEntity(List<Tag> ts){
        List<TagDTO> tags = new ArrayList<>();
        for(int i=0;i<ts.size();i++){
            tags.add(TagDTO.fromEntity(ts.get(i)));
        }
        return tags;
    }

}
