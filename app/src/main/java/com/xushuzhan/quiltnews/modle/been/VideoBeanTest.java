package com.xushuzhan.quiltnews.modle.been;

/**
 * Created by xushuzhan on 2016/8/17.
 */
public class VideoBeanTest {

    /**
     * reason : 查询成功
     * result : {"mp4":"http://k.youku.com/player/getFlvPath/sid/1471419420303206d73a9_00/st/mp4/fileid/030020010057B2F4B84C5B094C47B69A5C17A3-256F-C7AE-9DA3-D21DF094B5EE?k=b3f0d0a70887ceb1282ba821&hd=1&myp=0&ts=559&ymovie=1&r=s0KfeG/MpJGxT8zBRdWN/IHAQeg1csU4bRevfhnqJ69HhhGO5uGRXlPXEggYozuy8Wr49JU2xtmUf/AMFDUIvm/knpR/VP2gvRiJUn9refKc8Fy79iq1AedoAsxTqriv&type=&sid=1471419420303206d73a9&token=2251&oip=1863149221&did=14ed9531fc2feb2d2ba0fded673843f1&ctype=20&ev=1&ep=3ZhnQDzVTkaDiHTz5tqGGg8DlFjJGFTmPBH8KjB5aK%2FMjl%2BwhVfS3oep2BDBeoDtpxM937dvXJ1trAbpozfqZzMoHwgnGwYN%2FG3qBh4QmQctRV82JCMT%2BY44U0zTlY9o","m3u8":"http://pl.youku.com/playlist/m3u8?ts=1471419420&keyframe=1&vid=XMTY4NzE3NzMyOA==&type=mp4&sid=1471419420303206d73a9&token=2251&oip=1863149221&ly=yunyouflvku&&did=14ed9531fc2feb2d2ba0fded673843f1&ctype=20&ev=1&ep=3ZhnQDzVTkaDiHTz5tqGGqJW4f9hLbTePWcYMyDXC4XMDUpHSa8oALxCZAlM3Tmx"}
     * error_code : 0
     */

    private String reason;
    /**
     * mp4 : http://k.youku.com/player/getFlvPath/sid/1471419420303206d73a9_00/st/mp4/fileid/030020010057B2F4B84C5B094C47B69A5C17A3-256F-C7AE-9DA3-D21DF094B5EE?k=b3f0d0a70887ceb1282ba821&hd=1&myp=0&ts=559&ymovie=1&r=s0KfeG/MpJGxT8zBRdWN/IHAQeg1csU4bRevfhnqJ69HhhGO5uGRXlPXEggYozuy8Wr49JU2xtmUf/AMFDUIvm/knpR/VP2gvRiJUn9refKc8Fy79iq1AedoAsxTqriv&type=&sid=1471419420303206d73a9&token=2251&oip=1863149221&did=14ed9531fc2feb2d2ba0fded673843f1&ctype=20&ev=1&ep=3ZhnQDzVTkaDiHTz5tqGGg8DlFjJGFTmPBH8KjB5aK%2FMjl%2BwhVfS3oep2BDBeoDtpxM937dvXJ1trAbpozfqZzMoHwgnGwYN%2FG3qBh4QmQctRV82JCMT%2BY44U0zTlY9o
     * m3u8 : http://pl.youku.com/playlist/m3u8?ts=1471419420&keyframe=1&vid=XMTY4NzE3NzMyOA==&type=mp4&sid=1471419420303206d73a9&token=2251&oip=1863149221&ly=yunyouflvku&&did=14ed9531fc2feb2d2ba0fded673843f1&ctype=20&ev=1&ep=3ZhnQDzVTkaDiHTz5tqGGqJW4f9hLbTePWcYMyDXC4XMDUpHSa8oALxCZAlM3Tmx
     */

    private ResultBean result;
    private int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean {
        private String mp4;
        private String m3u8;

        public String getMp4() {
            return mp4;
        }

        public void setMp4(String mp4) {
            this.mp4 = mp4;
        }

        public String getM3u8() {
            return m3u8;
        }

        public void setM3u8(String m3u8) {
            this.m3u8 = m3u8;
        }
    }
}
