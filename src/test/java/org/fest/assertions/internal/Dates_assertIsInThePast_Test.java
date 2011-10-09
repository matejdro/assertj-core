/*
 * Created on Dec 24, 2010
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright @2010-2011 the original author or authors.
 */
package org.fest.assertions.internal;

import static org.fest.assertions.error.ShouldBeInThePast.shouldBeInThePast;
import static org.fest.assertions.test.FailureMessages.actualIsNull;
import static org.fest.assertions.test.TestData.someInfo;
import static org.fest.assertions.test.TestFailures.failBecauseExpectedAssertionErrorWasNotThrown;

import static org.mockito.Mockito.verify;

import java.util.Date;

import org.junit.*;

import org.fest.assertions.core.AssertionInfo;

/**
 * Tests for <code>{@link Dates#assertIsInThePast(AssertionInfo, Date)}</code>.
 *
 * @author Joel Costigliola
 */
public class Dates_assertIsInThePast_Test extends AbstractDatesTest {

  @Override
  @Before
  public void setUp() {
    super.setUp();
  }

  @Test
  public void should_fail_if_actual_is_not_in_the_past() {
    AssertionInfo info = someInfo();
    try {
      actual = parseDate("2111-01-01");
      dates.assertIsInThePast(info, actual);
    } catch (AssertionError e) {
      verify(failures).failure(info, shouldBeInThePast(actual));
      return;
    }
    failBecauseExpectedAssertionErrorWasNotThrown();
  }

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expectAssertionError(actualIsNull());
    dates.assertIsInThePast(someInfo(), null);
  }

  @Test
  public void should_pass_if_actual_is_in_the_past() {
    actual = parseDate("2000-01-01");
    dates.assertIsInThePast(someInfo(), actual);
  }

}