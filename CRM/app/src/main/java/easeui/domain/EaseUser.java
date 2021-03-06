/**
 * Copyright (C) 2013-2014 EaseMob Technologies. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package easeui.domain;

import android.annotation.SuppressLint;

import com.easemob.chat.EMContact;

@SuppressLint("ParcelCreator")
public class EaseUser extends EMContact {
	private int unreadMsgCount;
    /**
     * 昵称首字母
     */
	protected String initialLetter;
	/**
	 * 用户头像
	 */
	protected String avatar;

	private String nick;
	private String pinyin;
	private String header;

	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	
	public EaseUser(String username){
	    this.username = username;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getInitialLetter() {
		return initialLetter;
	}

	public void setInitialLetter(String initialLetter) {
		this.initialLetter = initialLetter;
	}


	public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
	public int hashCode() {
		return 17 * getUsername().hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || !(o instanceof EaseUser)) {
			return false;
		}
		return getUsername().equals(((EaseUser) o).getUsername());
	}

	@Override
	public String toString() {
		return nick == null ? username : nick;
	}


	public void setUnreadMsgCount(int unreadMsgCount) {
		this.unreadMsgCount = unreadMsgCount;
	}

	public int getUnreadMsgCount() {
		return unreadMsgCount;
	}
}
