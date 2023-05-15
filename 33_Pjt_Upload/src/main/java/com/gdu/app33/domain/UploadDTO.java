package com.gdu.app33.domain;

import java.security.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadDTO {
	private int uploadNo;
	private String uploadTitle;
	private String uploadContent;
	private Timestamp createdAt;
	private Timestamp modifiedAt;
}
