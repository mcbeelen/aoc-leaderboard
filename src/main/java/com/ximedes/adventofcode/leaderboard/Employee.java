package com.ximedes.adventofcode.leaderboard;

public class Employee extends Member {

    private String avatarUrl;

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }


    public static final class EmployeeBuilder {
        private String id;
        private String avatarUrl;
        private Integer stars;
        private String name;

        private EmployeeBuilder() {
        }

        public static EmployeeBuilder anEmployee() {
            return new EmployeeBuilder();
        }

        public EmployeeBuilder withId(String id) {
            this.id = id;
            return this;
        }

        public EmployeeBuilder withAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
            return this;
        }

        public EmployeeBuilder withStars(Integer stars) {
            this.stars = stars;
            return this;
        }

        public EmployeeBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public Employee build() {
            Employee employee = new Employee();
            employee.setId(id);
            employee.setAvatarUrl(avatarUrl);
            employee.setStars(stars);
            employee.setName(name);
            return employee;
        }
    }
}
