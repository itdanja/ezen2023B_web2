import { useCallback, useMemo, useState } from "react";

export default function UseMemoApp( props ){


    const [ex1, setEx1] = useState(0);
    const [ex2, setEx2] = useState(0);
   
    // useMemo 사용하기
    useMemo(() => {console.log( 'ex1 : ' + ex1)}, [ex1]);

    // useCallback이 () => {console.log(ex2)}라는 함수를 반환합니다.
    const useCallbackReturn = useCallback(() => { console.log( 'ex2 : ' + ex2) }, [ex1]);
    // useCallback이 담겨있는 함수를 실행
    useCallbackReturn();

    return(<>
        <div>
            <button onClick={() => setEx1((curr1) => (curr1 + 1))}>X</button>
            <button onClick={() => setEx2((curr2) => (curr2 + 1))}>Y</button>
        </div>
    </>);

}
/*
[ useMemo vs useCallback ] 
- useMemo는 계산 비용이 큰 연산의 결과를 메모이제이션하여 재사용하고, useCallback은 함수를 메모이제이션하여 재사용합니다.

- useMemo는 연산 결과를 반환하고, useCallback은 함수를 반환합니다.

- useMemo는 값을 메모이제이션하고, useCallback은 함수를 메모이제이션합니다.

- useMemo는 값을 계산하는 로직을 콜백 함수에 작성하고, useCallback은 함수를 생성하는 로직을 콜백 함수에 작성합니다.

- useMemo는 계산 비용이 큰 연산을 최적화하는 데 사용됩니다. 예를 들어, 배열이나 객체와 같은 큰 데이터를 가공하거나 복잡한 계산을 수행하는 경우에 사용됩니다.

- useCallback은 자주 렌더링되는 컴포넌트에서 함수를 최적화하고, 불필요한 함수 재생성을 방지하는 데 사용됩니다. 예를 들어, 자식 컴포넌트에 전달되는 콜백 함수를 최적화하고 싶은 경우에 사용됩니다.

- 의존성 배열(dependencyArray)을 사용하여 어떤 값이 변경되었을 때에만 메모이제이션된 값이나 함수를 갱신하도록 설정할 수 있습니다. 이를 통해 불필요한 갱신을 방지하고, 성능을 향상할 수 있습니다.

- useMemo와 useCallback은 렌더링 결과에 영향을 주지 않는 경우에는 불필요한 메모이제이션을 방지하고, 최적화를 위해 사용되어야 합니다.

- useMemo와 useCallback은 성능 최적화를 위한 강력한 도구로 사용되며, 필요에 따라 적절하게 사용하여 React 애플리케이션의 성능을향상할수 있습니다.


*/