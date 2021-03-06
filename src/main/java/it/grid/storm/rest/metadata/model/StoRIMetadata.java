package it.grid.storm.rest.metadata.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_EMPTY)
public class StoRIMetadata {

	public enum ResourceType {
		FILE, FOLDER
	};

	public enum ResourceStatus {
		ONLINE, NEARLINE
	};

	private String absolutePath;
	private VirtualFSMetadata filesystem;
	private ResourceType type;
	private ResourceStatus status;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
	private Date lastModified;

	private List<String> children;

	private FileAttributes attributes;

	@JsonCreator
	public StoRIMetadata(@JsonProperty("absolutePath") String absolutePath,
			@JsonProperty("type") ResourceType type, @JsonProperty("status") ResourceStatus status,
			@JsonProperty("filesystem") VirtualFSMetadata filesystem,
			@JsonProperty("attributes") FileAttributes attributes,
			@JsonProperty("lastModified") Date lastModified,
			@JsonProperty("children") List<String> children) {
		this.absolutePath = absolutePath;
		this.type = type;
		this.status = status;
		this.filesystem = filesystem;
		this.attributes = attributes;
		this.lastModified = lastModified;
		this.children = children;
	}

	public StoRIMetadata(Builder builder) {
		this.absolutePath = builder.absolutePath;
		this.type = builder.type;
		this.status = builder.status;
		this.filesystem = builder.filesystem;
		this.attributes = builder.attributes;
		this.lastModified = builder.lastModified;
		this.children = builder.children;
	}

	public String getAbsolutePath() {
		return absolutePath;
	}

	public ResourceType getType() {
		return type;
	}

	public ResourceStatus getStatus() {
		return status;
	}

	public VirtualFSMetadata getFilesystem() {
		return filesystem;
	}

	public FileAttributes getAttributes() {
		return attributes;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public List<String> getChildren() {
		return children;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((absolutePath == null) ? 0 : absolutePath.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StoRIMetadata other = (StoRIMetadata) obj;
		if (absolutePath == null) {
			if (other.absolutePath != null)
				return false;
		} else if (!absolutePath.equals(other.absolutePath))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {

		private Date lastModified;
		private String absolutePath;
		private ResourceType type;
		private ResourceStatus status;
		private VirtualFSMetadata filesystem;
		private FileAttributes attributes;
		private List<String> children;

		public Builder absolutePath(String absolutePath) {
			this.absolutePath = absolutePath;
			return this;
		}

		public Builder type(ResourceType type) {
			this.type = type;
			return this;
		}

		public Builder status(ResourceStatus status) {
			this.status = status;
			return this;
		}

		public Builder filesystem(VirtualFSMetadata filesystem) {
			this.filesystem = filesystem;
			return this;
		}

		public Builder attributes(FileAttributes attributes) {
			this.attributes = attributes;
			return this;
		}

		public Builder lastModified(Date lastModified) {
			this.lastModified = lastModified;
			return this;
		}

		public Builder children(List<String> children) {
			this.children = children;
			return this;
		}

		public StoRIMetadata build() {
			return new StoRIMetadata(this);
		}
	}
}
