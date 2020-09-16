package com.example.lib.algorithm.Lambda;

public class Student {
    String name;
    String age;
    Achievement achievement;

    public Achievement getAchievement() {
        return achievement;
    }

    public void setAchievement(Achievement achievement) {
        this.achievement = achievement;
    }

    public Student(String name, String age, Achievement achievement) {
        this.name = name;
        this.age = age;
        this.achievement = achievement;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public static class Achievement {
        String Math;
        String Chinese;
        String English;
        boolean isPass;

        public Achievement(String math, String chinese, String english, boolean isPass) {
            Math = math;
            Chinese = chinese;
            English = english;
            this.isPass = isPass;
        }

        public boolean isPass() {
            return isPass;
        }

        public void setPass(boolean pass) {
            isPass = pass;
        }

        public String getMath() {
            return Math;
        }

        public void setMath(String math) {
            Math = math;
        }

        public String getChinese() {
            return Chinese;
        }

        public void setChinese(String chinese) {
            Chinese = chinese;
        }

        public String getEnglish() {
            return English;
        }

        public void setEnglish(String english) {
            English = english;
        }
    }
}