package racingcar

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.style.BehaviorSpec
import racingcar.domain.Cars
import racingcar.domain.condition.AlwaysMoveCondition
import racingcar.domain.condition.NotMoveCondition
import racingcar.domain.condition.RandomMoveCondition
import racingcar.domain.strategy.SatisfyAllConditionStrategy
import racingcar.domain.strategy.SatisfyRandomMoveStrategy
import racingcar.domain.strategy.SatisfySingleConditionMoveStrategy

internal class CarsTest : BehaviorSpec({
    Given("하나라도 만족하면 움직이는 차들이여도, ") {
        val carNames = listOf("car1", "car2", "car3")
        val moveConditions = listOf(RandomMoveCondition(), NotMoveCondition(), AlwaysMoveCondition())
        val cars = Cars(
            carNames = carNames,
            moveStrategy = SatisfySingleConditionMoveStrategy(moveConditions),
        )
        When("어떤 조건이든, ") {
            Then("정상적으로 동작한다.") {
                shouldNotThrowAny {
                    cars.move()
                }
            }
        }
    }

    Given("모든 조건을 만족하면 움직이는 차들이라면, ") {
        val carNames = listOf("car1", "car2", "car3")
        val moveConditions = listOf(RandomMoveCondition(), NotMoveCondition(), AlwaysMoveCondition())
        val cars = Cars(
            carNames = carNames,
            moveStrategy = SatisfyAllConditionStrategy(moveConditions),
        )
        When("어떤 조건이든, ") {
            Then("정상적으로 동작한다.") {
                shouldNotThrowAny {
                    cars.move()
                }
            }
        }
    }

    Given("랜덤 조건을 만족하면 움직이는 차들이라면, ") {
        val carNames = listOf("car1", "car2", "car3")
        val cars = Cars(
            carNames = carNames,
            moveStrategy = SatisfyRandomMoveStrategy(),
        )
        When("랜덤 조건에 맞게, ") {
            Then("정상적으로 동작한다.") {
                shouldNotThrowAny {
                    cars.move()
                }
            }
        }
    }
})
