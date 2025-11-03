package lotto.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class WinningNumber {

    private final List<Integer> numbers;

    public WinningNumber(String inputValue) {
        this.numbers = validate(inputValue);
    }

    public List<Integer> getNumbers() {
        return List.copyOf(numbers);
    }

    private List<Integer> validate(String inputValue) {
        List<Integer> numbers = parseAndValidate(inputValue);
        validateDuplicates(numbers);
        validateNumberRangeAndCount(numbers);
        return numbers;
    }

    public static List<Integer> parseAndValidate(String inputValue) {
        if (inputValue == null || inputValue.isBlank()) {
            return List.of();
        }
        if (!inputValue.matches("^[0-9,]+$")) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 문자열이 포함되어 있습니다.");
        }
        return Arrays.stream(inputValue.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private void validateDuplicates(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if (uniqueNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException("[ERROR] 로또 당첨 번호는 중복이 있을 수 없습니다.");
        }
    }

    private void validateNumberRangeAndCount(List<Integer> numbers) {
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) <= 0 || numbers.get(i) > 45) {
                throw new IllegalArgumentException("[ERROR] 로또 당첨 숫자는 1 ~ 45까지의 숫자이어야 합니다.");
            }
        }
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 당첨 번호는 6개여야 합니다.");
        }
    }
}
