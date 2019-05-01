package org.feedback.service.api.dto;

public class GenericResponseDto {
	public enum ResponseEnum {
		SUCCESS("success"),
		NEGATIVE_COMMENT("feeback is objectional to comany/product/service"),
		POSITIVE_COMMENT("Thanks for positive response");
		private final String message;

		ResponseEnum(final String message) {
			this.message = message;
		}

		@Override
		public String toString() {
			return message;
		}
	}
}
