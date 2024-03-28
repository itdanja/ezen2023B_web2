import Card from "./Card";

export default function ProfileCard(props) {
    return (
        <>
            <Card title="Inje Lee" backgroundColor="skyblue">
                <p>안녕하세요, 소플입니다.</p>
                <p>저는 리액트를 사용해서 개발하고 있습니다.</p>
            </Card>

            <Card title="JaeSuk Yoo" backgroundColor="red">
                <p>안녕하세요, 유재석입니다..</p>
                <p>저는 스프링을 사용해서 개발하고 있습니다.</p>
            </Card>
        </>
    );
}
