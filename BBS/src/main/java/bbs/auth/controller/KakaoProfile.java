package bbs.auth.controller;

import lombok.Data;

@Data
public class KakaoProfile {
	private String id;
	private KakaoAccount Kakao_account;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public KakaoAccount getKakao_account() {
		return Kakao_account;
	}

	public void setKakao_account(KakaoAccount kakao_account) {
		Kakao_account = kakao_account;
	}

	@Data
	public class KakaoAccount {
		private Profile profile;
		private String email;
		private boolean has_email;

		public Profile getProfile() {
			return profile;
		}

		public void setProfile(Profile profile) {
			this.profile = profile;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public boolean isHas_email() {
			return has_email;
		}

		public void setHas_email(boolean has_email) {
			this.has_email = has_email;
		}

		@Data
		public class Profile {
			private String nickname;
			private String profile_image_url;

			public String getNickname() {
				return nickname;
			}

			public void setNickname(String nickname) {
				this.nickname = nickname;
			}

			public String getProfile_image_url() {
				return profile_image_url;
			}

			public void setProfile_image_url(String profile_image_url) {
				this.profile_image_url = profile_image_url;
			}

		}
	}
}
