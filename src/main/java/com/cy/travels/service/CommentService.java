package com.cy.travels.service;

import com.cy.travels.model.dto.CommentDTO;
import com.cy.travels.model.dto.CommentRespDTO;
import com.cy.travels.utils.dto.PageBean;
import com.cy.travels.utils.dto.PageRequest;

public interface CommentService {
    int like(CommentDTO commentDTO);

    PageBean<CommentRespDTO> listPage(PageRequest<CommentDTO> request);

    int save(CommentDTO commentDTO);
}
