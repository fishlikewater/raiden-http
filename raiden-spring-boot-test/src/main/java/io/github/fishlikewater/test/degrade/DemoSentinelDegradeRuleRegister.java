/*
 * Copyright © 2024 ${owner} (fishlikewater@126.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.fishlikewater.test.degrade;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import io.github.fishlikewater.raiden.http.core.degrade.sentinel.SentinelDegradeRule;
import io.github.fishlikewater.raiden.http.core.degrade.sentinel.SentinelDegradeRuleRegister;
import io.github.fishlikewater.raiden.http.core.degrade.sentinel.SentinelDegradeRuleRegistry;
import org.springframework.stereotype.Component;

/**
 * {@code DemoSentinelDegradeRuleRegister}
 *
 * @author zhangxiang
 * @since 2024/12/13
 */
@Component
public class DemoSentinelDegradeRuleRegister implements SentinelDegradeRuleRegister {
    @Override
    public void register(SentinelDegradeRuleRegistry registry) {
        SentinelDegradeRule rule = new SentinelDegradeRule();
        rule.setGrade(RuleConstant.DEGRADE_GRADE_RT);
        rule.setCount(10);
        rule.setMinRequestAmount(3);
        rule.setTimeWindow(2);
        rule.setSlowRatioThreshold(1.0);
        registry.register("test", rule);
    }
}
