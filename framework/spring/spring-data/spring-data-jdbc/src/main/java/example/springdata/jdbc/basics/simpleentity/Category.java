/*
 * Copyright 2017-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package example.springdata.jdbc.basics.simpleentity;

import example.springdata.jdbc.basics.aggregate.AgeGroup;
import example.springdata.jdbc.basics.aggregate.LegoSet;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;

import java.time.LocalDateTime;

/**
 * Coarse classification for {@link LegoSet}s, like "Car", "Plane", "Building" and so on.
 *
 * @author Jens Schauder
 */
public class Category {

    private final @Id
    Long id;
    private String name, description;
    private LocalDateTime created;
    private long inserted;
    private AgeGroup ageGroup;

    @PersistenceConstructor
    private Category(
            Long id, String name, String description, LocalDateTime created, long inserted
            , AgeGroup ageGroup) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created = created;
        this.inserted = inserted;
        this.ageGroup = ageGroup;
    }

    public Category(String name, String description, AgeGroup ageGroup) {

        this.id = null;
        this.name = name;
        this.description = description;
        this.ageGroup = ageGroup;
        this.created = LocalDateTime.now();
    }

    public void timeStamp() {

        if (inserted == 0) {
            inserted = System.currentTimeMillis();
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public long getInserted() {
        return inserted;
    }

    public void setInserted(long inserted) {
        this.inserted = inserted;
    }

    public AgeGroup getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(AgeGroup ageGroup) {
        this.ageGroup = ageGroup;
    }

	public Category withId(long l) {
    	return this.id == l ? this : new Category(l, null, null, null, 0L, null);
	}
}
