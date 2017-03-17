package com.expect.custom.data.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expect.custom.data.dataobject.Attachment;

/**
 * 附件JPA
 */
public interface AttachmentRepository extends JpaRepository<Attachment, String> {

	/**
	 * 根据ids获取多个附件
	 */
	public List<Attachment> findByIdIn(String[] ids);

}
